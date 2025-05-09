package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.controllers.dto.AuthCreateRequest;
import org.alkemy.alkywallet.controllers.dto.AuthLoginRequest;
import org.alkemy.alkywallet.controllers.dto.AuthResponse;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.repositories.UsuarioRepository;
import org.alkemy.alkywallet.utils.JwtUtils;
import org.alkemy.alkywallet.utils.Rol;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuarioExistente = repository
                .findUsuarioByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el email: " + username + " no existe"));

        List<GrantedAuthority> authorities = new ArrayList<>();


        usuarioExistente
                .getRol()
                .forEach(rol -> authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.name().toUpperCase())));

        return new User(
                usuarioExistente.getEmail(),
                usuarioExistente.getContrasenia(),
                true,
                true,
                true,
                true,
                authorities);
    }


    public AuthResponse loginUser(AuthLoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User logged succesfuly", accessToken, true);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }


    //TODO: terminar de configurar el registro
    public AuthResponse registerUser(AuthCreateRequest registerRequest) {
        String email = registerRequest.email();
        String password = registerRequest.password();
        List<String> roles = registerRequest.roleRequest().roleListName();


        //FIXME: se rompe en el parseo
        //TODO: Tabla de Roles (CRUD) para hacer la comparacion
        List<Rol> rolList = roles.stream()
                .map(r -> Rol.valueOf(r.toUpperCase()))
                .toList();


        Usuario usuario = Usuario.builder()
                .nombre(registerRequest.nombre())
                .apellido(registerRequest.apellido())
                .email(email)
                .contrasenia(password)
                .rol(rolList)
                .build();

        Usuario usuarioCreado = repository.save(usuario);

        List<GrantedAuthority> authorityList = new ArrayList<>();

        usuarioCreado.getRol()
                .forEach(rol -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + rol.name().toUpperCase())));


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, authorityList);

        String token = jwtUtils.createToken(authentication);

        return new AuthResponse(email, "User registered succesfuly", token, true);
    }

}

package org.alkemy.alkywallet.services;

import org.alkemy.alkywallet.auth.utils.JwtUtils;
import org.alkemy.alkywallet.controllers.dto.AuthCreateRequest;
import org.alkemy.alkywallet.controllers.dto.AuthCreateRoleRequest;
import org.alkemy.alkywallet.controllers.dto.AuthLoginRequest;
import org.alkemy.alkywallet.controllers.dto.AuthResponse;
import org.alkemy.alkywallet.models.Rol;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.repositories.RoleRepository;
import org.alkemy.alkywallet.repositories.UsuarioRepository;
import org.alkemy.alkywallet.utils.RoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CuentaServiceImpl cuentaService;

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Test
    @DisplayName("Debería cargar usuario por email exitosamente")
    void shouldLoadUserByUsernameSuccessfully() {
        // Given
        AuthLoginRequest request = new AuthLoginRequest("test@email.com", "password123");
        Rol userRole = new Rol();
        userRole.setRole(RoleEnum.USER);

        Usuario usuario = Usuario.builder()
                .idUsuario(1L)
                .email("test@email.com")
                .contrasenia("encoded-password")
                .roles(Set.of(userRole))
                .build();

        // soporta múltiples llamadas
        when(usuarioRepository.findByEmail("test@email.com")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "encoded-password")).thenReturn(true);
        when(jwtUtils.createToken(any(Authentication.class))).thenReturn("jwt-token");

        // When
        AuthResponse result = userDetailService.loginUser(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.username()).isEqualTo("test@email.com");
        assertThat(result.message()).isEqualTo("User logged succesfuly");
        assertThat(result.jwt()).isEqualTo("jwt-token");
        assertThat(result.status()).isTrue();

        // Verificar que se llamó 2 veces: una en loginUser y otra en loadUserByUsername
        verify(usuarioRepository, times(3)).findByEmail("test@email.com");
        verify(passwordEncoder).matches("password123", "encoded-password");
        verify(jwtUtils).createToken(any(Authentication.class));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando usuario no existe")
    void shouldThrowExceptionWhenUserNotFound() {
        // Given
        String email = "nonexistent@email.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> userDetailService.loadUserByUsername(email))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("El usuario con el email: " + email + " no existe");

        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    @DisplayName("Debería hacer login exitosamente")
    void shouldLoginUserSuccessfully() {
        // Given
        AuthLoginRequest request = new AuthLoginRequest("test@email.com", "password123");

        Rol userRole = new Rol();
        userRole.setRole(RoleEnum.USER);

        Usuario usuario = Usuario.builder()
                .idUsuario(1L)
                .email("test@email.com")
                .contrasenia("encoded-password")
                .roles(Set.of(userRole))
                .build();

        when(usuarioRepository.findByEmail("test@email.com"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "encoded-password"))
                .thenReturn(true);
        when(jwtUtils.createToken(any(Authentication.class)))
                .thenReturn("jwt-token");

        // When
        AuthResponse result = userDetailService.loginUser(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.username()).isEqualTo("test@email.com");
        assertThat(result.message()).isEqualTo("User logged succesfuly");
        assertThat(result.jwt()).isEqualTo("jwt-token");
        assertThat(result.status()).isTrue();

        verify(usuarioRepository).findByEmail("test@email.com");
        verify(passwordEncoder).matches("password123", "encoded-password");
        verify(jwtUtils).createToken(any(Authentication.class));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando contraseña es incorrecta")
    void shouldThrowExceptionWhenPasswordIsIncorrect() {
        // Given
        AuthLoginRequest request = new AuthLoginRequest("test@email.com", "wrong-password");

        Usuario usuario = Usuario.builder()
                .email("test@email.com")
                .contrasenia("encoded-password")
                .roles(Set.of(new Rol()))
                .build();

        when(usuarioRepository.findByEmail("test@email.com"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrong-password", "encoded-password"))
                .thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> userDetailService.loginUser(request))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid password");

        verify(usuarioRepository).findByEmail("test@email.com");
        verify(passwordEncoder).matches("wrong-password", "encoded-password");
    }

    @Test
    @DisplayName("Debería registrar usuario exitosamente")
    void shouldRegisterUserSuccessfully() {
        // Given
        AuthCreateRoleRequest roleRequest = new AuthCreateRoleRequest(List.of("USER"));
        AuthCreateRequest request = new AuthCreateRequest(
                "Juan",
                "Pérez",
                "juan@email.com",
                "password123",
                roleRequest
        );

        Rol userRole = new Rol();
        userRole.setRole(RoleEnum.USER);

        Usuario usuarioGuardado = Usuario.builder()
                .idUsuario(1L)
                .nombre("Juan")
                .apellido("Pérez")
                .email("juan@email.com")
                .contrasenia("encoded-password")
                .roles(Set.of(userRole))
                .build();

        when(roleRepository.findRolesByRoleIn(List.of("USER")))
                .thenReturn(List.of(userRole));
        when(passwordEncoder.encode("password123"))
                .thenReturn("encoded-password");
        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuarioGuardado);
        when(jwtUtils.createToken(any(Authentication.class)))
                .thenReturn("jwt-token");

        // When
        AuthResponse result = userDetailService.registerUser(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.username()).isEqualTo("juan@email.com");
        assertThat(result.message()).isEqualTo("User registered succesfuly");
        assertThat(result.jwt()).isEqualTo("jwt-token");
        assertThat(result.status()).isTrue();


        verify(roleRepository).findRolesByRoleIn(List.of("USER"));
        verify(passwordEncoder).encode("password123");
        verify(usuarioRepository).save(any(Usuario.class));
        verify(cuentaService).crearCuentAInicialApartirDeUsuario(1L);
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando roles son inválidos")
    void shouldThrowExceptionWhenRolesAreInvalid() {
        // Given
        AuthCreateRoleRequest roleRequest = new AuthCreateRoleRequest(List.of("INVALID_ROLE"));
        AuthCreateRequest request = new AuthCreateRequest(
                "Juan",
                "Pérez",
                "juan@email.com",
                "password123",
                roleRequest
        );

        when(roleRepository.findRolesByRoleIn(List.of("INVALID_ROLE")))
                .thenReturn(List.of()); // Lista vacía

        // When & Then
        assertThatThrownBy(() -> userDetailService.registerUser(request))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid roles");

        verify(roleRepository).findRolesByRoleIn(List.of("INVALID_ROLE"));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
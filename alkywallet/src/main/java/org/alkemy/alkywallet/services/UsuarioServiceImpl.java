package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEstado().equals(true)).toList();
    }

    public Usuario obtenerUsuarioPorId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id: " + id + " no existe"));
    }

    public void crearUsuario(Usuario usuario) {

        Usuario newUsuario = new Usuario();

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        newUsuario.setNombre(usuario.getNombre());
        newUsuario.setApellido(usuario.getApellido());
        newUsuario.setEmail(usuario.getEmail());
        newUsuario.setContrasenia(usuario.getContrasenia());
        newUsuario.setRol(usuario.getRol());
        newUsuario.setFechaActualizacion(LocalDateTime.now());

        usuarioRepository.save(newUsuario);
    }

    public Usuario actualizarUsuarioPorId(Long id, Usuario usuario) {

        Usuario usuarioActualizado = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id: " + id + " no existe"));


        usuarioActualizado.setNombre(
                (usuario.getNombre() != null) ? usuario.getNombre() : usuarioActualizado.getNombre()
        );

        usuarioActualizado.setApellido(
                (usuario.getApellido() != null) ? usuario.getApellido() : usuarioActualizado.getApellido()
        );

        usuarioActualizado.setEmail(
                (usuario.getEmail() != null) ? usuario.getEmail() : usuarioActualizado.getEmail()
        );

        usuarioActualizado.setContrasenia(
                (usuario.getContrasenia() != null) ? usuario.getContrasenia() : usuarioActualizado.getContrasenia()
        );

        usuarioActualizado.setRol(
                (usuario.getRol() != null) ? usuario.getRol() : usuarioActualizado.getRol()
        );

        usuarioActualizado.setEstado(
                (usuario.getEstado() != null) ? usuario.getEstado() : usuarioActualizado.getEstado()
        );

        usuarioActualizado.setFechaActualizacion(LocalDateTime.now());

        return usuarioRepository.save(usuarioActualizado);

    }

    public void eliminarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id: " + id + " no existe"));
        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }
}

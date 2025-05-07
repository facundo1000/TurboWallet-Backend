package org.alkemy.alkywallet.controllers;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.services.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioControllerImpl {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.obtenerTodosUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorEstado(@RequestParam Boolean estado) {
        return new ResponseEntity<>(usuarioService.obtenerUsuariosPorEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(id), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Void> crearUsuario(@RequestBody Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioPorId(@PathVariable Long id, @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.actualizarUsuarioPorId(id, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioService.eliminarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

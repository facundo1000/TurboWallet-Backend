package org.alkemy.alkywallet.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.services.UsuarioServiceImpl;
import org.alkemy.alkywallet.swagger.find.ApiResponseObtenerUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@Tag(name = "UsuarioController", description = "controlador de usuarios")
public class UsuarioControllerImpl {

    private final UsuarioServiceImpl usuarioService;

    @ApiResponseObtenerUsuario
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.obtenerTodosUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorEstado() {
        return new ResponseEntity<>(usuarioService.obtenerUsuariosActivos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(id), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            List<Map<String, String>> list = result.getAllErrors().stream().map(error -> {
                return Map.of("field", Objects.requireNonNull(error.getCode()), "message", error.getDefaultMessage());
            }).toList();
            return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
        }

        usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioPorId(@PathVariable Long id, @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.actualizarUsuarioPorId(id, usuario), HttpStatus.OK);
    }

    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioService.eliminarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

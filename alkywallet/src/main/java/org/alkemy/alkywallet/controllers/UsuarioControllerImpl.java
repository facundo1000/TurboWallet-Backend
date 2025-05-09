package org.alkemy.alkywallet.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "UsuarioController", description = "controlador de usuarios")
public class UsuarioControllerImpl {

    private final UsuarioServiceImpl usuarioService;

    @Operation(summary = "Metodo que retorna una lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"
                    , content = {
                    @Content(mediaType = "text/plain")
            }),
            @ApiResponse(responseCode = "401", description = "Forbidden"
                    , content = {
                    @Content(mediaType = "text/plain")
            })
    })
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
    public ResponseEntity<Void> crearUsuario(@RequestBody Usuario usuario) {
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

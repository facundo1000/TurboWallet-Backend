package org.alkemy.alkywallet.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.services.UsuarioServiceImpl;
import org.alkemy.alkywallet.swagger.find.ApiResponseObtenerUsuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@Tag(name = "UsuarioController", description = "Controlador Rest de Usuarios")
public class UsuarioControllerImpl {

    private final UsuarioServiceImpl usuarioService;

    /**
     * Funcion que retorna todos los usuarios registrados en la base de datos
     *
     * @return List<Usuario>
     */

    @ApiResponseObtenerUsuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.obtenerTodosUsuarios(), HttpStatus.OK);
    }

    /**
     * Funcion que retorna todos los usuarios registrados en la base de datos, segun su estado
     *
     * @return List<Usuario>
     */
    @Operation(summary = "Metodo que retorna a todos los usuarios registrados en sistema, segun su estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"
                    , content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Forbidden"
                    , content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorEstado() {
        return new ResponseEntity<>(usuarioService.obtenerUsuariosActivos(), HttpStatus.OK);
    }

    /**
     * Funcion que retorna un usuario registrado en la base de datos, segun su id.
     *
     * @param id Long
     * @return Usuario
     */
    @Operation(summary = "Metodo que retorna a todos los usuarios registrados en sistema, a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"
                    , content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Forbidden"
                    , content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(id), HttpStatus.OK);
    }


    /**
     * Funcion que actualiza un usuario registrado en la base de datos, segun su id.
     *
     * @param id      Long
     * @param usuario Usuario
     * @return Usuario
     */

    @Operation(summary = "Metodo que actualiza a un usuario registrado en sistema, segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"
                    , content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Forbidden"
                    , content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioPorId(@PathVariable Long id, @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.actualizarUsuarioPorId(id, usuario), HttpStatus.OK);
    }

    /**
     * Funcion que actualiza el 'estado' de un usuario registrado en la base de datos, segun su id.
     *
     * @param id Long
     * @return Void
     */
    @Operation(summary = "Metodo que actualiza el 'estado' de un usuario registrado en sistema, segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"
                    , content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Forbidden"
                    , content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioService.eliminarUsuarioPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

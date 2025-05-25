package org.alkemy.alkywallet.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.controllers.dto.CuentaDto;
import org.alkemy.alkywallet.services.CuentaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cuentas")
@Tag(name = "CuentaController", description = "controlador de cuentas del sistema")
public class CuentaControllerImpl {

    private final CuentaServiceImpl cuentaService;

    @Operation(summary = "Metodo que retorna a todas las cuentas registradas en sistema")
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

    @GetMapping
    public ResponseEntity<List<CuentaDto>> obtenerTodosCuentas() {
        return new ResponseEntity<>(cuentaService.obtenerTodos(), HttpStatus.OK);
    }


    @Operation(summary = "Metodo que retorna a todas las cuentas registradas en sistema activas de usuarios activos")
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

    @GetMapping("/usuario/{idUsuario}/activas")
    public ResponseEntity<List<CuentaDto>> obtenerCuentasActivasPorUsuario(@PathVariable Long idUsuario) {
        return new ResponseEntity<>(cuentaService.obtenerCuentasActivasPorUsuario(idUsuario), HttpStatus.OK);
    }


    @Operation(summary = "Metodo que retorna a todas las cuentas registradas en sistema filtradas por ACTIVA a partir de un usuario registrado en sistema")
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
    @GetMapping("/activas")
    public ResponseEntity<List<CuentaDto>> obtenerPorEstado() {
        return new ResponseEntity<>(cuentaService.obtenerPorEstado(), HttpStatus.OK);
    }

    @Operation(summary = "Metodo que retorna a una cuenta registradas en sistema por ID")
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
    public ResponseEntity<CuentaDto> obtenerCuentaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(cuentaService.obtenerPorId(id), HttpStatus.OK);
    }

    /*
        @Operation(summary = "Metodo que crea una cuenta en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente"
                    , content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Forbidden"
                    , content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PostMapping("/crear/{idUsuario}")
    public ResponseEntity<CuentaDto> crearCuenta(@PathVariable Long idUsuario) {
        return new ResponseEntity<>(cuentaService.crearCuentApartirDeUsuario(idUsuario), HttpStatus.CREATED);
    }
     */


    // TODO: hacer el servicio PRIMERO
    //@PutMapping("/actualizar/{id}")

    /**
     * public ResponseEntity<Cuenta> actualizarCuentaPorId(@PathVariable Long id, @RequestBody Cuenta cuenta) {
     * return new ResponseEntity<>(cuentaService.actualizar(cuenta,id), HttpStatus.OK);
     * }
     */

    @Operation(summary = "Metodo que genera un soft-delete sobre una cuenta registrada en sistema por ID")
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
    public ResponseEntity<Void> eliminarCuentaPorId(@PathVariable Long id) {
        cuentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

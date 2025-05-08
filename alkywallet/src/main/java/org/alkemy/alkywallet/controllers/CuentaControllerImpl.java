package org.alkemy.alkywallet.controllers;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.services.CrudService;
import org.alkemy.alkywallet.services.CuentaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cuentas")
public class CuentaControllerImpl {

    private final CuentaServiceImpl cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodosCuentas() {
        return new ResponseEntity<>(cuentaService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Cuenta>> obtenerPorEstado(@RequestParam Boolean estado) {
        return new ResponseEntity<>(cuentaService.obtenerPorEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(cuentaService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.crear(cuenta), HttpStatus.CREATED);
    }

    /**TODO: hacer el servicio PRIMERO
     @PutMapping("/actualizar/{id}")
     public ResponseEntity<Cuenta> actualizarCuentaPorId(@PathVariable Long id, @RequestBody Cuenta cuenta) {
     return new ResponseEntity<>(cuentaService.actualizar(cuenta), HttpStatus.OK);
     }
     */


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCuentaPorId(@PathVariable Long id) {
        cuentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

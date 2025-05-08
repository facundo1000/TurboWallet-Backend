package org.alkemy.alkywallet.controllers;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Tarjeta;
import org.alkemy.alkywallet.services.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tarjetas")
@RequiredArgsConstructor
public class TarjetaControllerImpl {

    private final CrudService<Tarjeta> tarjetaService;


    @GetMapping
    public ResponseEntity<List<Tarjeta>> obtenerTodosTarjetas() {
        return new ResponseEntity<>(tarjetaService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Tarjeta>> obtenerPorEstado() {
        return new ResponseEntity<>(tarjetaService.obtenerPorEstado(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> obtenerTarjetaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tarjetaService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody Tarjeta tarjeta) {
        return new ResponseEntity<>(tarjetaService.crear(tarjeta), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}") //TODO: generar el servicio correspondiente
    public ResponseEntity<Tarjeta> actualizarTarjetaPorId(@PathVariable Long id, @RequestBody Tarjeta tarjeta) {
        return new ResponseEntity<>(tarjetaService.actualizar(tarjeta, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminarTarjetaPorId(@PathVariable Long id) {
        tarjetaService.eliminar(id);
        return new ResponseEntity<>(Map.of("message", "Tarjeta ID: " + id), HttpStatus.NO_CONTENT);
    }
}

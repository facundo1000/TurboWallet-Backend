package org.alkemy.alkywallet.controllers;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Tarjeta;
import org.alkemy.alkywallet.services.TarjetaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controllador Rest para la entidad Tarjeta
 * <br>
 * autor: Squad2
 */

@RestController
@RequestMapping("/api/v1/tarjetas")
@RequiredArgsConstructor
public class TarjetaControllerImpl {

    private final TarjetaServiceImpl tarjetaService;

    /**
     * Obtiene todas las tarjetas registradas en la base de datos
     * @return ResponseEntity con el listado de tarjetas
     */

    @GetMapping
    public ResponseEntity<List<Tarjeta>> obtenerTodosTarjetas() {
        return new ResponseEntity<>(tarjetaService.obtenerTodos(), HttpStatus.OK);
    }

    /**
     * Funcion para obtener todas las tarjetas activas en la base de datos
     * @return ResponseEntity con el listado de tarjetas activas
     */

    @GetMapping("/activas")
    public ResponseEntity<List<Tarjeta>> obtenerPorEstado() {
        return new ResponseEntity<>(tarjetaService.obtenerPorEstado(), HttpStatus.OK);
    }

    /**
     * Funcion para obtener una tarjeta por su id en la base de datos
     * @param id
     * @return ResponseEntity con la tarjeta encontrada o HttpStatus.NOT_FOUND si no se encuentra ninguna tarjeta con ese id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> obtenerTarjetaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tarjetaService.obtenerPorId(id), HttpStatus.OK);
    }

    /**
     * Funcion para crear una tarjeta en la base de datos
     * @param tarjeta
     * @return ResponseEntity con la tarjeta creada o HttpStatus.BAD_REQUEST si se proporcionaron datos erroneos
     */

    @PostMapping("/crear")
    public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody Tarjeta tarjeta) {
        return new ResponseEntity<>(tarjetaService.crear(tarjeta), HttpStatus.CREATED);
    }

    /**
     * Funcion para actualizar una tarjeta en la base de datos
     * @param tarjeta
     * @param id
     * @return ResponseEntity con la tarjeta actualizada o HttpStatus.BAD_REQUEST si se proporcionaron datos erroneos
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Tarjeta> actualizarTarjetaPorId(@PathVariable Long id, @RequestBody Tarjeta tarjeta) {
        return new ResponseEntity<>(tarjetaService.actualizar(tarjeta, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminarTarjetaPorId(@PathVariable Long id) {
        tarjetaService.eliminar(id);
        return new ResponseEntity<>(Map.of("message", "Tarjeta ID: " + id), HttpStatus.NO_CONTENT);
    }
}

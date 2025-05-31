package org.alkemy.alkywallet.controllers;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.controllers.dto.TransferenciaDto;
import org.alkemy.alkywallet.models.Transferencia;

import org.alkemy.alkywallet.services.TransferenciaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaServiceImpl transferenciaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> obtenerTodasLasTransferencias() {
        return ResponseEntity.ok(transferenciaService.obtenerTodasLasTransferencias());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Transferencia>> obtenerTransferenciasPorEstado(@PathVariable Boolean estado) {
        return ResponseEntity.ok(transferenciaService.obtenerTransferenciasPorEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> obtenerTransferenciaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transferenciaService.obtenerTransferenciaPorId(id));
    }

    @PostMapping("/tarjeta/{idTarjeta}")
    public ResponseEntity<Transferencia> realizarTransferencia(@RequestBody TransferenciaDto transferenciaDto, @PathVariable Long idTarjeta) {
        Transferencia transferencia = transferenciaService.realizarTransferencia(transferenciaDto, idTarjeta);
        return new ResponseEntity<>(transferencia, HttpStatus.CREATED);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancelarTransferencia(@PathVariable Long id) {
        transferenciaService.cancelarTransferencia(id);
        return ResponseEntity.noContent().build();
    }
}
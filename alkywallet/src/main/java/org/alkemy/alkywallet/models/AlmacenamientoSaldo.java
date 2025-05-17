package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Clase AlmacenamientoSaldo.
 * <br>
 * Representa las operaciones relacionadas con almacenamiento de saldo,
 * vinculadas a comisiones u operaciones internas.
 *
 * @author Escuadron404
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "almacenamiento_saldo")
public class AlmacenamientoSaldo extends Transaccion {

    @Column(name = "codigo_referencia")
    private UUID codReferencia;

    @Column(name = "comision_aplicada")
    private Float comisionAplicada;

    @ManyToOne
    @Transient
    private Cuenta cuenta;


    /**
     * Funcion para inicializar las variables de la clase Transaccion
     */
    @PrePersist
    private void init() {
        this.setEstado(true);
        this.setFecha(LocalDateTime.now());
    }
}

package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Clase Deposito
 * <br>
 * Registra las transacciones que son depositos es decir movimientos donde se ingresan fondos al sistema
 *
 * @author Escuadron404
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "deposito")
public class Deposito extends Transaccion {

    private String canal;

    @Column(name = "origen_de_fondos")
    private String origenDeFondos;

    @Column(name = "id_externo")
    private UUID idExterno;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Transient
    private Cuenta cuenta;


    /**
     * Funcion para inicializar las variables de la clase Transaccion
     */
    @PrePersist
    private void init() {
        this.setEstado(true);
        this.setFecha(LocalDateTime.now());
        this.idExterno = UUID.randomUUID();
    }
}

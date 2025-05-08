package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Transaccion model
 * <br>
 * Autor: Squad2
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    @Setter(AccessLevel.NONE)
    private Long idTransaccion;

    private String monto;

    private LocalDateTime fecha;

    @Column(name = "medio_de_pago")
    private String medioDePago;

    private Boolean estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @Transient
    private Cuenta cuenta;

    @ManyToOne(cascade = CascadeType.ALL)
    @Transient
    private Tarjeta tarjeta;

    /**
     * Funcion para inicializar las variables de la clase Transaccion
     */
    @PrePersist
    private void init(){
        this.estado = true;
        this.fecha = LocalDateTime.now();
    }
}

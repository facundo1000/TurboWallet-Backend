package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Clase Modelo Transferencia.
 * <br>
 * Es la clase en cargada del modelo aplicado a las transferencias entre cuentas bancarias.
 *
 * @author Escuadron404
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transferencia")
public class Transferencia extends Transaccion {

    @Column(name = "tipo_transferencia")
    private String tipoTransferencia;

    private String motivo;

    @Column(name = "nombre_destinatario")
    private String nombreDestinatario;

    @Column(name = "banco_destino")
    private String bancoDestino;

    @Column(name = "cuenta_destinatario")
    private String cuentaDestinatario;

    @Column(name = "cuenta_origen")
    private String cuentaOrigen;

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
        this.cuentaOrigen = cuenta.getCbu();
        this.setMedioDePago("TRANSFERENCIA");
    }


}

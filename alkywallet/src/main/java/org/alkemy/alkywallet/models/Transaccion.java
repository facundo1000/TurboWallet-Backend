package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Transaccion model
 * <br>
 * @author: Escuadron404
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    @Setter(AccessLevel.NONE)
    private Long idTransaccion;

    @NotBlank
    private String monto;

    private LocalDateTime fecha;

    @Column(name = "medio_de_pago")
    private String medioDePago;

    private Boolean estado;
}

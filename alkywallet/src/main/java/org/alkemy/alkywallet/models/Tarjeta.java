package org.alkemy.alkywallet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idTarjeta;

    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;

    @Column(name = "nombre_titular")
    private String nombreTitular;

    private String cvv;

    private String banco;

    private String tipo;

    @Column(name = "tope_gasto")
    private String topeGasto;

    @Column(name = "fecha_vencimiento")
    @Setter(AccessLevel.NONE)
    private LocalDate fechaVencimiento;

    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @PrePersist
    private void init() {
        this.estado = true;
        this.fechaVencimiento = randomDate();
    }


    private LocalDate randomDate() {

        // Fechas límite
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.of(2025, 12, 31);

        // Número de días entre las fechas
        long days = ChronoUnit.DAYS.between(start, end);

        // Generar un número aleatorio de días y sumarlo a la fecha inicial
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);

        return start.plusDays(randomDays);
    }

}

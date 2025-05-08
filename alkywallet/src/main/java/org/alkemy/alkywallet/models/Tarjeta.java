package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
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
    @Column(name = "id_tarjeta")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @Transient
    private Cuenta cuenta;

    @OneToMany
    @JoinTable(
            name = "tbl_tarjeta_transacciones",
            joinColumns = @JoinColumn(name = "id_tarjeta"),
            inverseJoinColumns = @JoinColumn(name = "id_transaccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_tarjeta", "id_transaccion"})
    )
    private List<Transaccion> transaccions;

    @PrePersist
    private void init() {
        this.estado = true;
        this.fechaVencimiento = randomDate();
        this.numeroTarjeta = generarNumeroTarjeta();
        this.cvv = generarCVV();
    }


    /**
     * Funcion para generar una fecha aleatoria entre 2026 y 2050.
     * @return LocalDate object
     */
    private LocalDate randomDate() {

        // Fechas límite
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2050, 12, 31);

        // Número de días entre las fechas
        long days = ChronoUnit.DAYS.between(start, end);

        // Generar un número aleatorio de días y sumarlo a la fecha inicial
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);

        return start.plusDays(randomDays);
    }


    /**
     * Funcion para generar un numero aleatorio de Tarjeta
     * @return String object con el numero de tarjeta generado.
     */
    private String generarNumeroTarjeta() {
        // Implementar lógica para generar número único de 16 dígitos
        // Ejemplo básico:
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    /**
     * Funcion para generar CVV de forma aleatoria.
     * @return String object con el CVV generado.
     */
    private String generarCVV() {
        // Implementar lógica para generar CVV de 3 dígitos
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }

}

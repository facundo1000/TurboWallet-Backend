package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.*;
import org.alkemy.alkywallet.utils.TipoTarjeta;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Enumerated(EnumType.STRING)
    private TipoTarjeta tipo; //CREDITO - DEBITO - NATIVA

    @Column(name = "tope_gasto")
    private String topeGasto;

    @Column(name = "fecha_vencimiento")
    @Setter(AccessLevel.NONE)
    private LocalDate fechaVencimiento;

    private Boolean estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @Transient
    private Cuenta cuenta;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "tbl_tarjeta_transferencia",
            joinColumns = @JoinColumn(name = "id_tarjeta"),
            inverseJoinColumns = @JoinColumn(name = "id_transaccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_tarjeta", "id_transaccion"})
    )
    private Set<Transferencia> transferencias;

    @PrePersist
    private void init() {
        this.estado = true;
        this.fechaVencimiento = randomDate();
        this.numeroTarjeta = generarNumeroTarjeta();
        this.cvv = generarCVV();
        this.tipo = TipoTarjeta.NATIVA;
        this.banco = "Banco Bancario";
    }


    /**
     * Funcion para generar una fecha aleatoria entre 2026 y 2050.
     *
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
     *
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
     *
     * @return String object con el CVV generado.
     */
    private String generarCVV() {
        // Implementar lógica para generar CVV de 3 dígitos
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }

}

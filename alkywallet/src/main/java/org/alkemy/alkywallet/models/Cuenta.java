package org.alkemy.alkywallet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long idCuenta;

    private String cbu;

    private String saldo;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura;

    @ManyToOne
    @JoinTable(
            name = "tbl_usuario_cuentas",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_cuenta"})
    )
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_cuenta_tarjeta",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_tarjeta"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_cuenta", "id_tarjeta"})
    )
    @JsonIgnore
    Set<Tarjeta> tarjetas = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_cuenta_deposito",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_transaccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_cuenta", "id_transaccion"})
    )
    private Set<Deposito> deposito;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_cuenta_transferencia",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_transaccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_cuenta", "id_transaccion"})
    )
    private Set<Transferencia> transferencia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_cuenta_almacenamiento",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_transaccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_cuenta", "id_transaccion"})
    )
    private Set<AlmacenamientoSaldo> almacenamientoSaldo;

    private Boolean estado;

    @PrePersist
    private void init() {
        this.estado = true;
        this.fechaApertura = LocalDateTime.now();
        this.cbu = generarCBU();
        this.saldo = "0.00";
    }

    /**
     * Funcion para generar CBU de manera automatica
     * El CBU son 22 numeros compuestos de 5 segmentos que representan
     * <br>
     * entidad - sucursal - ID cuenta - DV2
     * <br>
     * <a href="https://www.bcra.gob.ar/mediospago/Clave-Bancaria-Uniforme.asp">Documentacion sobre CBU</a>
     *
     * @return String object
     */
    private String generarCBU() {
        StringBuilder cbu = new StringBuilder();
        java.util.Random random = new java.util.Random();

        // Entidad bancaria (3 dígitos)
        for (int i = 0; i < 3; i++) {
            cbu.append(random.nextInt(10));
        }

        // Sucursal (4 dígitos)
        for (int i = 0; i < 4; i++) {
            cbu.append(random.nextInt(10));
        }

        // Dígito verificador 1 (1 dígito)
        cbu.append(random.nextInt(10));

        // Número de cuenta (13 dígitos)
        for (int i = 0; i < 13; i++) {
            cbu.append(random.nextInt(10));
        }

        // Dígito verificador 2 (1 dígito)
        cbu.append(random.nextInt(10));

        return cbu.toString();
    }


}

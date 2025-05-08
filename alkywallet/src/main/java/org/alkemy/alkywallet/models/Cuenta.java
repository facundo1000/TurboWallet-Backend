package org.alkemy.alkywallet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany
    @JoinTable(
            name = "tbl_cuenta_tarjeta",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_tarjeta"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_cuenta", "id_tarjeta"})
    )
    @JsonIgnore
    Set<Tarjeta> tarjetas = new HashSet<>();

    @OneToMany
    @JoinTable(
            name = "tbl_cuenta_transaccion",
            joinColumns = @JoinColumn(name = "id_cuenta"),
            inverseJoinColumns = @JoinColumn(name = "id_transaccion"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_cuenta", "id_transaccion"})
    )
    private List<Transaccion> transaccion;

    private Boolean estado;

    @PrePersist
    private void init() {
        this.estado = true;
        this.fechaApertura = LocalDateTime.now();
    }

}

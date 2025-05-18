package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "Error. Coloque un nombre de usuario")
    private String nombre;

    @NotBlank
    private String apellido;

    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    private String contrasenia;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_usuario_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_rol"})
    )
    private Set<Rol> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private Set<Cuenta> cuentas = new HashSet<>();

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    private Boolean estado;


    @PrePersist
    private void init() {
        this.fechaRegistro = LocalDateTime.now();
        this.estado = true;
    }

}

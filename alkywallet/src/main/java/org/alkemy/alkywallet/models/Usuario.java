package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.alkemy.alkywallet.utils.Rol;

import java.time.LocalDateTime;

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

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private Rol rol;

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

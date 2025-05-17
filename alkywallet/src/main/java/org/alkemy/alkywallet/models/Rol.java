package org.alkemy.alkywallet.models;

import jakarta.persistence.*;
import lombok.*;
import org.alkemy.alkywallet.utils.RoleEnum;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;


    @Enumerated(EnumType.STRING)
    @Column(name = "rol_nombre")
    private RoleEnum role;

}

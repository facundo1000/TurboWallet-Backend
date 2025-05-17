package org.alkemy.alkywallet.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private String nombre;

    private String apellido;

    private String email;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

    private Boolean estado;
}

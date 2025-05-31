package org.alkemy.alkywallet.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.alkemy.alkywallet.utils.MarcaTarjeta;
import org.alkemy.alkywallet.utils.TipoTarjeta;

import java.time.LocalDate;
import java.util.Set;


public record TarjetaDto(
        Long idTarjeta,
        @NotBlank
        String nombreTitular,
        @NotBlank
        String cvv,
        @NotBlank
        String banco,
        @NotBlank
        String numeroTarjeta,
        @NotNull
        TipoTarjeta tipo,
        @NotBlank
        String topeGasto,
        @NotNull
        LocalDate fechaVencimiento,
        @NotNull
        MarcaTarjeta marca,
        Boolean estado,
        Set<TransferenciaDto> transferenciaDto
) {
}

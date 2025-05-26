package org.alkemy.alkywallet.controllers.dto;

import org.alkemy.alkywallet.utils.MarcaTarjeta;
import org.alkemy.alkywallet.utils.TipoTarjeta;

import java.time.LocalDate;
import java.util.Set;


public record TarjetaDto(String nombreTitular,
                         String cvv,
                         String banco,
                         String numeroTarjeta,
                         TipoTarjeta tipo,
                         String topeGasto,
                         LocalDate fechaVencimiento,
                         MarcaTarjeta marca,
                         Boolean estado,
                         Set<TransferenciaDto> transferenciaDto
) {
}

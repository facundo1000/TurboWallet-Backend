package org.alkemy.alkywallet.controllers.dto;

import java.time.LocalDate;
import java.util.Set;

//FIXME: no matche con el transferenciaDto
public record TarjetaDto(String nombreTitular,
                         String cvv,
                         String banco,
                         String tipo,
                         String topeGasto,
                         LocalDate fechaVencimiento,
                         Boolean estado,
                         Set<TransferenciaDto> transferenciaDto
) {
}

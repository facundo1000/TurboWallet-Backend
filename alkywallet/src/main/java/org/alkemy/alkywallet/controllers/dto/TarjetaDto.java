package org.alkemy.alkywallet.controllers.dto;

import java.time.LocalDate;

public record TarjetaDto(String nombreTitular,
                         String cvv,
                         String banco,
                         String tipo,
                         String topeGasto,
                         LocalDate fechaVencimiento,
                         Boolean estado
) {
}

package org.alkemy.alkywallet.controllers.dto;

import java.util.UUID;

public record DepositoDto(
        String canal,
        String origenDeFondos,
        UUID idExterno,
        Boolean estado) {
}

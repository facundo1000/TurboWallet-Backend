package org.alkemy.alkywallet.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank(message = "ingresar un email valido")
                               @Schema(description = "user email", example = "juan.perez@example.com")
                               String username,
                               @Schema(description = "user password", example = "1234")
                               @NotBlank(message = "ingrese un password valido")
                               String password) {
}

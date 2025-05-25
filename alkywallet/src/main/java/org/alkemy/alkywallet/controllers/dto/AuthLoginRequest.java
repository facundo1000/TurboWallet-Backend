package org.alkemy.alkywallet.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static org.alkemy.alkywallet.constanst.ApplicationConstasts.PASS_REGEX;

public record AuthLoginRequest(@NotBlank(message = "ingresar un email valido")
                               @Schema(description = "user email", example = "juan.perez@example.com")
                               @Email(message = "ingrese un email valido")
                               String username,
                               @Schema(description = "user password", example = "1234")
                               @NotBlank(message = "ingrese un password valido")
                               @Pattern(regexp = PASS_REGEX)
                               String password) {
}

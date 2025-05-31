package org.alkemy.alkywallet.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthCreateRequest(
        @NotBlank(message = "debe ingresar un email")
        @Email(message = "ingresar un email valido", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        String email,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String password,
        @NotNull AuthCreateRoleRequest roleRequest) {
}

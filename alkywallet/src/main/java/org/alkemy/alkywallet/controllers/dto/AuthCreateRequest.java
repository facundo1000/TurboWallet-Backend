package org.alkemy.alkywallet.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthCreateRequest(@NotBlank String email,
                                @NotBlank String nombre,
                                @NotBlank String apellido,
                                @NotBlank String password,
                                @NotNull AuthCreateRoleRequest roleRequest) {
}

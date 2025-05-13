package org.alkemy.alkywallet.controllers.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequest(@Size(max = 3, message = "el usuario no debe tener mas de 3 roles")
                                    @Schema(description = "roles list", example = "[\"ADMIN\"]")
                                    List<String> roleListName) {}
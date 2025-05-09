package org.alkemy.alkywallet.controllers.dto;


import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequest(@Size(max = 3, message = "user cannot have more than 3 roles")
                                    List<String> roleListName) {}
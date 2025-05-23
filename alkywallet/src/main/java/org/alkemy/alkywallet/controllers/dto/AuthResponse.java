package org.alkemy.alkywallet.controllers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status","id"})
public record AuthResponse(String username, String message, String jwt, boolean status, Long id) {
}

package org.alkemy.alkywallet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alkemy.alkywallet.controllers.dto.AuthCreateRequest;
import org.alkemy.alkywallet.controllers.dto.AuthCreateRoleRequest;
import org.alkemy.alkywallet.controllers.dto.AuthLoginRequest;
import org.alkemy.alkywallet.controllers.dto.AuthResponse;
import org.alkemy.alkywallet.services.UserDetailServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthControllerImpl.class)
class AuthControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debería hacer login exitosamente")
    void shouldLoginSuccessfully() throws Exception {
        // Given
        AuthLoginRequest request = new AuthLoginRequest("test@email.com", "password123");
        AuthResponse expectedResponse = new AuthResponse(
                "test@email.com",
                "User logged succesfuly",
                "jwt-token",
                true,
                1L
        );

        when(userDetailService.loginUser(any(AuthLoginRequest.class)))
                .thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("test@email.com"))
                .andExpect(jsonPath("$.message").value("User logged succesfuly"))
                .andExpect(jsonPath("$.jwt").value("jwt-token"))
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.idUsuario").value(1));

        verify(userDetailService).loginUser(any(AuthLoginRequest.class));
    }

    @Test
    @DisplayName("Debería registrar usuario exitosamente")
    void shouldRegisterUserSuccessfully() throws Exception {
        // Given
        AuthCreateRoleRequest roleRequest = new AuthCreateRoleRequest(List.of("USER"));
        AuthCreateRequest request = new AuthCreateRequest(
                "Juan",
                "Pérez",
                "juan@email.com",
                "password123",
                roleRequest
        );

        AuthResponse expectedResponse = new AuthResponse(
                "juan@email.com",
                "User registered succesfuly",
                "jwt-token",
                true,
                2L
        );

        when(userDetailService.registerUser(any(AuthCreateRequest.class)))
                .thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("juan@email.com"))
                .andExpect(jsonPath("$.message").value("User registered succesfuly"))
                .andExpect(jsonPath("$.idUsuario").value(2));

        verify(userDetailService).registerUser(any(AuthCreateRequest.class));
    }

    @Test
    @DisplayName("Debería renovar token exitosamente")
    void shouldRenewTokenSuccessfully() throws Exception {
        // Given
        String token = "valid-jwt-token";
        AuthResponse expectedResponse = new AuthResponse(
                "test@email.com",
                "Token renovado correctamente",
                "new-jwt-token",
                true,
                1L
        );

        when(userDetailService.renewToken(token)).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/api/v1/auth/renew")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Token renovado correctamente"))
                .andExpect(jsonPath("$.jwt").value("new-jwt-token"));

        verify(userDetailService).renewToken(token);
    }

    @Test
    @DisplayName("Debería retornar error 400 cuando datos de login son inválidos")
    void shouldReturn400WhenLoginDataIsInvalid() throws Exception {
        // Given
        AuthLoginRequest invalidRequest = new AuthLoginRequest("", "");

        // When & Then
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}
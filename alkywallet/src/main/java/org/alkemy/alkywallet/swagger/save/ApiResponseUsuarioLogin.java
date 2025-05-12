package org.alkemy.alkywallet.swagger.save;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Ingreso de un usuario registrado en el sistema")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario ingresado correctamente",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Error en el procesado del request",
                content = @Content),
        @ApiResponse(responseCode = "401", description = "Request No Autorizado",
                content = @Content(mediaType = "text/plain")),
        @ApiResponse(responseCode = "403", description = "Request Prohibido",
                content = @Content(mediaType = "text/plain"))
})
public @interface ApiResponseUsuarioLogin {
}

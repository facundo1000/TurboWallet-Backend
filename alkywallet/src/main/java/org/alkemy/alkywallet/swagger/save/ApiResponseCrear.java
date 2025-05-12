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
@Operation(summary = "Crea un nuevo usuario en el sistema (credentiales incluidas)")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario agregado exitosamente",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Error. Request procesado erroneamente",
                content = @Content),
        @ApiResponse(responseCode = "401", description = "Request No Autorizado",
                content = @Content(mediaType = "text/plain")),
        @ApiResponse(responseCode = "403", description = "Forbidden request",
                content = @Content(mediaType = "text/plain"))
})
public @interface ApiResponseCrear {
}

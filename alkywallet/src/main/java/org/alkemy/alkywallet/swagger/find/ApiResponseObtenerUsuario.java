package org.alkemy.alkywallet.swagger.find;

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
@Operation(summary = "Metodo que retorna una lista de todos los usuarios")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"
                , content = {
                @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "401", description = "Forbidden"
                , content = {
                @Content(mediaType = "application/json")
        })
})
public @interface ApiResponseObtenerUsuario {
}

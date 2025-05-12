package org.alkemy.alkywallet.swagger.find;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Operation(summary = "Metodo que retorna una lista de todos los usuarios")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se retornan todos los usuarios"
                , content = {
                @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "401", description = "Forbidden"
                , content = {
                @Content(mediaType = "application/json")
        })
})
public @interface ApiResponseObtenerUsuarios {
}

package org.alkemy.alkywallet.mapper;

import org.alkemy.alkywallet.controllers.dto.UsuarioDto;
import org.alkemy.alkywallet.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
}

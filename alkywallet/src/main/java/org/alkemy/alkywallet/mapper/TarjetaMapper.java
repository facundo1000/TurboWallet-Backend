package org.alkemy.alkywallet.mapper;

import org.alkemy.alkywallet.controllers.dto.TarjetaDto;
import org.alkemy.alkywallet.models.Tarjeta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarjetaMapper {

    TarjetaDto tarjetaToTarjetaDto(Tarjeta tarjeta);
}

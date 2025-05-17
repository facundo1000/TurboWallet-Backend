package org.alkemy.alkywallet.mapper;

import org.alkemy.alkywallet.controllers.dto.TarjetaDto;
import org.alkemy.alkywallet.models.Tarjeta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TransaccionMapper.class})
public interface TarjetaMapper {

    @Mapping(source = "transferencias", target = "transferenciaDto")
    TarjetaDto tarjetaToTarjetaDto(Tarjeta tarjeta);
}

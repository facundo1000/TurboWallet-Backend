package org.alkemy.alkywallet.mapper;

import org.alkemy.alkywallet.controllers.dto.CuentaDto;
import org.alkemy.alkywallet.models.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UsuarioMapper.class,
        TarjetaMapper.class,
        TransaccionMapper.class
})
public interface CuentaMapper {

    @Mapping(source = "usuario", target = "usuarioDto")
    @Mapping(source = "tarjetas", target = "tarjetasDto")
    @Mapping(source = "deposito", target = "depositoDto")
    @Mapping(source = "transferencia", target = "transferenciaDto")
    @Mapping(source = "almacenamientoSaldo", target = "almacenmientoDto")
    CuentaDto cuentaToCuentaDto(Cuenta cuenta);

    List<CuentaDto> cuentaListToCuentaDtoList(List<Cuenta> cuentas);

}

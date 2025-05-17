package org.alkemy.alkywallet.mapper;

import org.alkemy.alkywallet.controllers.dto.AlmacenmientoDto;
import org.alkemy.alkywallet.controllers.dto.DepositoDto;
import org.alkemy.alkywallet.controllers.dto.TransferenciaDto;
import org.alkemy.alkywallet.models.AlmacenamientoSaldo;
import org.alkemy.alkywallet.models.Deposito;
import org.alkemy.alkywallet.models.Transferencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    DepositoDto depositoToDepositoDto(Deposito deposito);

    TransferenciaDto transferenciaToTransferenciaDto(Transferencia transferencia);

    AlmacenmientoDto almacenmientoToAlmacenmientoDto(AlmacenamientoSaldo saldo);
}

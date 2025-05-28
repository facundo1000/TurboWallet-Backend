package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.controllers.dto.TarjetaDto;
import org.alkemy.alkywallet.mapper.TarjetaMapper;
import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.models.Tarjeta;
import org.alkemy.alkywallet.repositories.CuentaRepository;
import org.alkemy.alkywallet.repositories.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarjetaServiceImpl {

    private final TarjetaRepository tarjetaRepository;

    private final CuentaRepository cuentaRepository;

    private final TarjetaMapper tarjetaMapper;

    public List<TarjetaDto> obtenerTodos() {
        return tarjetaRepository.findAll().stream().map(tarjetaMapper::tarjetaToTarjetaDto).toList();
    }


    public List<TarjetaDto> obtenerPorEstado() {
        return tarjetaRepository.findAll()
                .stream()
                .filter(t -> t.getEstado().equals(true))
                .map(tarjetaMapper::tarjetaToTarjetaDto)
                .toList();
    }


    public Tarjeta obtenerPorId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("El id de la tarjeta no puede ser nulo");
        }

        return tarjetaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La tarjeta con el id: " + id + " no existe"));
    }


    public Tarjeta agregarNuevaTarjetaACuenta(Tarjeta tarjeta, Long idCuenta) {

        Cuenta cuentaExistente = cuentaRepository
                .findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta con el id: " + idCuenta + " no existe"));

        if (tarjeta == null) {
            throw new IllegalArgumentException("Error en la creacion de la tarjeta");
        }

        Tarjeta newTarjeta = Tarjeta.builder()
                .numeroTarjeta(tarjeta.getNumeroTarjeta())
                .nombreTitular(tarjeta.getNombreTitular())
                .tipo(tarjeta.getTipo())
                .banco(tarjeta.getBanco())
                .marca(tarjeta.getMarca())
                .topeGasto(cuentaExistente.getSaldo())
                .cuenta(cuentaExistente)
                .fechaVencimiento(tarjeta.getFechaVencimiento())
                .cvv(tarjeta.getCvv())
                .estado(true)
                .build();

        cuentaExistente.getTarjetas().add(newTarjeta);

        return tarjetaRepository.save(newTarjeta);
    }


    public Tarjeta actualizar(Tarjeta tarjeta, Long id) {

        //TODO: asociar al cuenta con la tarjeta

        Tarjeta tarjetaActualizada = tarjetaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La tarjeta con el id: " + id + " no existe"));

        if (tarjeta == null) {
            throw new IllegalArgumentException("Error en la actualizacion de la tarjeta");
        }

        tarjetaActualizada.setNombreTitular(tarjeta.getNombreTitular());
        tarjetaActualizada.setBanco(tarjeta.getBanco());
        tarjetaActualizada.setTipo(tarjeta.getTipo());
        tarjetaActualizada.setTopeGasto(tarjeta.getTopeGasto());

        return tarjetaRepository.save(tarjetaActualizada);
    }


    public void eliminar(Long id) {
        Tarjeta tarjeta = tarjetaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La tarjeta con el id: " + id + " no existe"));
        tarjeta.setEstado(false);
        tarjeta.getCuenta().getTarjetas().forEach(t -> t.setEstado(false));
        tarjetaRepository.save(tarjeta);
    }
}

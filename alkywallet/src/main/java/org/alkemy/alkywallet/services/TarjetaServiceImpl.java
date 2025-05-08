package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Tarjeta;
import org.alkemy.alkywallet.repositories.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarjetaServiceImpl {

    private final TarjetaRepository tarjetaRepository;


    public List<Tarjeta> obtenerTodos() {
        return tarjetaRepository.findAll();
    }


    public List<Tarjeta> obtenerPorEstado() {
        return tarjetaRepository.findAll()
                .stream()
                .filter(t -> t.getEstado().equals(true))
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


    public Tarjeta crear(Tarjeta tarjeta) {

        //TODO: asociar al cuenta con la tarjeta

        if(tarjeta == null) {
            throw new IllegalArgumentException("Error en la creacion de la tarjeta");
        }

        Tarjeta newTarjeta = new Tarjeta();

        newTarjeta.setNombreTitular(tarjeta.getNombreTitular());
        newTarjeta.setBanco(tarjeta.getBanco());
        newTarjeta.setTipo(tarjeta.getTipo());
        newTarjeta.setTopeGasto(tarjeta.getTopeGasto());
        newTarjeta.setCuenta(null);

        return tarjetaRepository.save(newTarjeta);
    }


    public Tarjeta actualizar(Tarjeta tarjeta,Long id) {

        //TODO: asociar al cuenta con la tarjeta

        Tarjeta tarjetaActualizada = tarjetaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La tarjeta con el id: " + id + " no existe"));

        if(tarjeta == null){
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
        tarjetaRepository.save(tarjeta);
    }
}

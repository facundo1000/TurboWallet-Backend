package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl {

    private final CuentaRepository cuentaRepository;


    public List<Cuenta> obtenerTodos() {
        return cuentaRepository.findAll();
    }


    public List<Cuenta> obtenerPorEstado(Boolean estado) {
        return cuentaRepository.findAll().stream().filter(c -> c.getEstado().equals(estado)).toList();
    }


    public Cuenta obtenerPorId(Long id) {
        return cuentaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta con el id: " + id + " no existe"));
    }


    public Cuenta crear(Cuenta cuenta) {

        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }

        Cuenta newCuenta = new Cuenta();

        newCuenta.setCbu(cuenta.getCbu());
        newCuenta.setSaldo(cuenta.getSaldo());

        newCuenta.setUsuario(null); //TODO: Resolver como toma al usuario que crea la cuenta

        newCuenta.setTarjetas(new HashSet<>());

        return cuentaRepository.save(newCuenta);
    }

    //TODO: hacer el "actualizar"

    public Cuenta actualizar(Cuenta cuenta) {
        return null;
    }

    public void eliminar(Long id) {
        Cuenta cuenta = cuentaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta con el id: " + id + " no existe"));
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
    }

}

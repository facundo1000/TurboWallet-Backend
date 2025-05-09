package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.repositories.CuentaRepository;
import org.alkemy.alkywallet.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl {

    private final CuentaRepository cuentaRepository;

    private final UsuarioRepository usuarioRepository;


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


    public Cuenta crearCuentApartirDeUsuario(Long idUsuario) {

        Usuario usuarioExistente = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id: " + idUsuario + " no existe"));

        Cuenta newCuenta = new Cuenta();

        newCuenta.setUsuario(usuarioExistente);

        newCuenta.setTarjetas(new HashSet<>());

        return cuentaRepository.save(newCuenta);
    }

    //TODO: Resolver si existe caso de uso valido para este metodo
    public Cuenta crearSinUsuario(Cuenta cuenta) {

        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }

        Cuenta newCuenta = new Cuenta();

        newCuenta.setCbu(cuenta.getCbu());
        newCuenta.setSaldo(cuenta.getSaldo());

        newCuenta.setUsuario(null);

        newCuenta.setTarjetas(new HashSet<>());

        return cuentaRepository.save(newCuenta);
    }

    //TODO: hacer el "actualizar". Analizar en que casos de uso corresponde.
    public Cuenta actualizar(Cuenta cuenta, Long idCuenta) {

        Cuenta cuentaRegistrada = cuentaRepository
                .findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta con el id: " + idCuenta + " no existe"));

        if (cuenta == null) {
            throw new IllegalArgumentException("Error en la actualizacion de la cuenta");
        }

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

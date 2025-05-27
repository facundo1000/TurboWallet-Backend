package org.alkemy.alkywallet.services;

import lombok.RequiredArgsConstructor;
import org.alkemy.alkywallet.controllers.dto.CuentaDto;
import org.alkemy.alkywallet.mapper.CuentaMapper;
import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.models.Tarjeta;
import org.alkemy.alkywallet.models.Usuario;
import org.alkemy.alkywallet.repositories.CuentaRepository;
import org.alkemy.alkywallet.repositories.UsuarioRepository;
import org.alkemy.alkywallet.utils.TipoMoneda;
import org.alkemy.alkywallet.utils.TipoTarjeta;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl {

    private final CuentaRepository cuentaRepository;

    private final UsuarioRepository usuarioRepository;

    private final CuentaMapper cuentaMapper;

    public List<CuentaDto> obtenerTodos() {

        return cuentaRepository.findAll()
                .stream()
                .map(cuentaMapper::cuentaToCuentaDto).toList();
    }

    public List<CuentaDto> obtenerPorEstado() {
        return cuentaRepository.findAll()
                .stream()
                .filter(c -> c.getEstado().equals(true))
                .map(cuentaMapper::cuentaToCuentaDto)
                .toList();
    }

    public List<CuentaDto> obtenerCuentasActivasPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

        if (usuario.getEstado() == null || !usuario.getEstado()) {
            throw new IllegalArgumentException("El usuario no está activo");
        }
        List<Cuenta> cuentasActivas = cuentaRepository.findAllByUsuarioAndEstado(usuario, true);

        return cuentasActivas
                .stream()
                .map(cuentaMapper::cuentaToCuentaDto)
                .toList();
    }


    public CuentaDto obtenerPorId(Long id) {
        return cuentaRepository
                .findById(id)
                .map(cuentaMapper::cuentaToCuentaDto)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta con el id: " + id + " no existe"));
    }


    /**
     * Metodo que sirve para la creacion de una cuenta a partir de un usuario registrado en sistema.
     * <br>
     * Y tambien que tipo de moneda se utilizara en la cuenta
     * @param idUsuario Long
     * @param tipoMoneda TipoMoneda
     * @return CuentaDto object
     */
    public CuentaDto crearCuentaNuevaApartirDeUsuario(Long idUsuario, TipoMoneda tipoMoneda) {
        //Se busca un usuario existente
        Usuario usuarioExistente = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id: " + idUsuario + " no existe"));



        //Cuenta incluida por defecto
        Cuenta newCuenta = new Cuenta();
        newCuenta.setSaldo("0.00");
        newCuenta.setUsuario(usuarioExistente);
        newCuenta.setMoneda(tipoMoneda);
        return cuentaMapper.cuentaToCuentaDto(cuentaRepository.save(newCuenta));
    }

    /**
     * Metodo que sirve para la creacion de una cuenta  inicial a partir de un usuario registrado en sistema.
     * <br>
     * La funcion no tiene retorno
     *
     * @param idUsuario Long
     */
    public void crearCuentAInicialApartirDeUsuario(Long idUsuario) {

        //Se busca un usuario existente
        Usuario usuarioExistente = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id: " + idUsuario + " no existe"));

        //Cuenta incluida por defecto
        Cuenta newCuenta = new Cuenta();
        newCuenta.setSaldo("0.00");

        //Tarjeta por defecto incluida con la cuenta
        Tarjeta tarjeta = Tarjeta.builder()
                .nombreTitular(usuarioExistente.getNombre().concat(" ").concat(usuarioExistente.getApellido()))
                .topeGasto(newCuenta.getSaldo())
                .tipo(TipoTarjeta.ALKYWALLET)
                .build();

        newCuenta.setUsuario(usuarioExistente);
        newCuenta.setTarjetas(Set.of(tarjeta));
        cuentaRepository.save(newCuenta);
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
        cuenta.getTarjetas().forEach(t -> t.setEstado(false));
        cuentaRepository.save(cuenta);
    }

}

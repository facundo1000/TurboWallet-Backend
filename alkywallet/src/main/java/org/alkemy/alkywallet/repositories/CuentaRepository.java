package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<List<Cuenta>> findCuentasByUsuario(Usuario usuario);

    List<Cuenta> findAllByUsuarioAndEstado(Usuario usuario, boolean estado);

    @Query("SELECT c FROM Cuenta c JOIN c.tarjetas t WHERE t.idTarjeta = :idTarjeta")
    Optional<Cuenta> findByTarjetaId(@Param("idTarjeta") Long idTarjeta);

}

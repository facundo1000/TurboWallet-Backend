package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Cuenta;
import org.alkemy.alkywallet.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<List<Cuenta>> findCuentasByUsuario(Usuario usuario);
}

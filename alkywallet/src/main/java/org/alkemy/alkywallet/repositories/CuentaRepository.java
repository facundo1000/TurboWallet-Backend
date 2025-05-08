package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}

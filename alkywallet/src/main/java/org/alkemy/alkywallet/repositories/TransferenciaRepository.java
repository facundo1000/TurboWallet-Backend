package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findAllByEstado(Boolean estado);

}

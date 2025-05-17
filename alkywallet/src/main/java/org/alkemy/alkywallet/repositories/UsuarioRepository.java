package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Funcion para buscar un usuario por email a la base de datos.
     * La clase optional permite trabajar mejor las excepciones de
     * manera funcional.
     *
     * @param email
     * @return Optional object
     */
    Optional<Usuario> findByEmail(String email);
}

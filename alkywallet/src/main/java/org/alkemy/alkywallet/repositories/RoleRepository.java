package org.alkemy.alkywallet.repositories;

import org.alkemy.alkywallet.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Rol, Long> {

    List<Rol> findRolesByRoleIn(List<String> roles);
}

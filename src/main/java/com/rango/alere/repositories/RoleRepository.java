package com.rango.alere.repositories;

import com.rango.alere.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByDescricaoIsLike(String descricao);

    Role findRoleByDescricaoIsLike(String descricao);

    Optional<Role> getRoleByDescricaoIsLike(String descricao);

}

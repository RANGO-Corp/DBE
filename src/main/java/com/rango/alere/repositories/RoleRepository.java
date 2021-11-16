package com.rango.alere.repositories;

import com.rango.alere.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByDescricaoIsLike(String descricao);

    Role findRoleByDescricaoIsLike(String descricao);

    Optional<Role> getRoleByDescricaoIsLike(String descricao);

    @Query("select r from Role r where r.isDefault = true")
    List<Role> getRoleByDefaultIsTrue();

}

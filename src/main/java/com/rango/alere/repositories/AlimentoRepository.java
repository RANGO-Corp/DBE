package com.rango.alere.repositories;

import com.rango.alere.entities.Alimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("select a from Alimento a where a.ativo = true")
    Page<Alimento> findAllByAtivoIsTrue(Pageable pageable);

}

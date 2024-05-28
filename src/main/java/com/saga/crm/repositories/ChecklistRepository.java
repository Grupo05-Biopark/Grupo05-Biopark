package com.saga.crm.repositories;

import com.saga.crm.model.Checklist;
import com.saga.crm.model.Eixo;
import com.saga.crm.model.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    List<Checklist> findByStatus(Integer status);

    @Query("SELECT c FROM Checklist c WHERE (:eixoId IS NULL OR c.eixo.id = :eixoId)")
    List<Checklist> findByEixo(@Param("eixoId") Integer eixoId);
}

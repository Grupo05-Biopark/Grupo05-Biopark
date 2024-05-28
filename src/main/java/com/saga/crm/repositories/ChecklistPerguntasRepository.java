package com.saga.crm.repositories;

import com.saga.crm.model.Checklist;
import com.saga.crm.model.ChecklistPerguntas;
import com.saga.crm.model.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChecklistPerguntasRepository extends JpaRepository<ChecklistPerguntas, Long> {



    @Query("SELECT p.titulo, p.descricao, e.titulo, s.titulo, pr.titulo FROM ChecklistPerguntas cp " +
            "INNER JOIN cp.checklist c " +
            "INNER JOIN cp.perguntas p " +
            "INNER JOIN p.eixo e " +
            "INNER JOIN p.setor s " +
            "INNER JOIN p.porte pr " +
            "WHERE c.id = :checklistId")
    List<Object[]> findPerguntasByChecklistId(@Param("checklistId") Long checklistId);


}

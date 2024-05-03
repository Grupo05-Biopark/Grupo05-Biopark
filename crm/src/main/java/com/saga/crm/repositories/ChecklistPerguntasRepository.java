package com.saga.crm.repositories;

import com.saga.crm.model.Checklist;
import com.saga.crm.model.ChecklistPerguntas;
import com.saga.crm.model.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChecklistPerguntasRepository extends JpaRepository<ChecklistPerguntas, Long> {


    List<ChecklistPerguntas> findByChecklist(Checklist checklist);
}

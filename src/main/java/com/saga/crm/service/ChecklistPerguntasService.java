package com.saga.crm.service;

import com.saga.crm.model.ChecklistPerguntas;
import com.saga.crm.repositories.ChecklistPerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistPerguntasService {
    private final ChecklistPerguntasRepository checklistPerguntasRepository;

    @Autowired
    public ChecklistPerguntasService(ChecklistPerguntasRepository checklistPerguntasRepository) {
        this.checklistPerguntasRepository = checklistPerguntasRepository;
    }

    public void save(ChecklistPerguntas checklistPerguntas) {
        checklistPerguntasRepository.save(checklistPerguntas);
    }


    public List<Object[]> perguntasByChecklist(Long id) {
        return checklistPerguntasRepository.findPerguntasByChecklistId(id);
    }

}


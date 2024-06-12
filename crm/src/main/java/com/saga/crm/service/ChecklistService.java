package com.saga.crm.service;

import com.saga.crm.model.Checklist;
import com.saga.crm.model.ChecklistPerguntas;
import com.saga.crm.model.Eixo;
import com.saga.crm.model.Perguntas;
import com.saga.crm.repositories.ChecklistPerguntasRepository;
import com.saga.crm.repositories.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final ChecklistPerguntasRepository checklistPerguntasRepository;

    @Autowired
    public ChecklistService(ChecklistRepository checklistRepository, ChecklistPerguntasRepository checklistPerguntasRepository) {
        this.checklistRepository = checklistRepository;
        this.checklistPerguntasRepository = checklistPerguntasRepository;
    }
    public Checklist getChecklistById(Long id) {
        return checklistRepository.findById(id).orElse(null);
    }

    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    public List<Checklist> getActiveChecklists() {
        return checklistRepository.findByStatus(1);
    }

    public void save(Checklist checklist) {
        checklistRepository.save(checklist);
    }


    public List<Checklist> getChecklistByEixo(Integer id) {
        return checklistRepository.findByEixo(id);
    }

}

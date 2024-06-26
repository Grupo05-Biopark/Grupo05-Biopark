package com.saga.crm.service;

import com.saga.crm.model.FormularioChecklist;
import com.saga.crm.repositories.FormularioChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioChecklistService {

    private final FormularioChecklistRepository formularioChecklistRepository;

    @Autowired
    public FormularioChecklistService(FormularioChecklistRepository formularioChecklistRepository) {
        this.formularioChecklistRepository = formularioChecklistRepository;
    }


    public void save(FormularioChecklist formularioChecklist) {
        formularioChecklistRepository.save(formularioChecklist);
    }

    public FormularioChecklist findByFormularioAndChecklist(Long formularioId, Long checklistId) {
        return formularioChecklistRepository.findByFormularioIdAndChecklistId(formularioId, checklistId);
    }

    public FormularioChecklist getFormularioChecklistById(Long id) {
        return formularioChecklistRepository.findById(id).orElse(null);
    }
}
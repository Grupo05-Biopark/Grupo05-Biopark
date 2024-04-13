package com.saga.crm.service;

import com.saga.crm.model.Perguntas;
import com.saga.crm.repositories.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntasService {

    private final PerguntasRepository perguntasRepository;

    @Autowired
    public PerguntasService(PerguntasRepository perguntasRepository) {
        this.perguntasRepository = perguntasRepository;
    }

    public List<Perguntas> getAllPerguntas() {
        return perguntasRepository.findAll();
    }
}

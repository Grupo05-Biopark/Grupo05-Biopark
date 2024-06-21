package com.saga.crm.service;

import com.saga.crm.model.Respostas;
import com.saga.crm.repositories.RespostasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RespostasService {
    private final RespostasRepository respostasRepository;

    public RespostasService(RespostasRepository respostasRepository) {
        this.respostasRepository = respostasRepository;
    }

    public void save(Respostas respostas) {
        respostasRepository.save(respostas);
    }
}

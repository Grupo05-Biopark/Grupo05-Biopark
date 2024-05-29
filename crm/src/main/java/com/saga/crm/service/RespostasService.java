package com.saga.crm.service;

import com.saga.crm.repositories.RespostasRepository;
import org.springframework.stereotype.Service;

@Service
public class RespostasService {
    private final RespostasRepository respostasRepository;

    public RespostasService(RespostasRepository respostasRepository) {
        this.respostasRepository = respostasRepository;
    }
}

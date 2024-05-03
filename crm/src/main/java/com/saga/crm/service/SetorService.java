package com.saga.crm.service;

import com.saga.crm.model.Setor;
import com.saga.crm.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    @Autowired
    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<Setor> getAllSetores() {
        return setorRepository.findAll();
    }

    public Setor getSetorById(Long id) {
        return setorRepository.findById(id).orElse(null);
    }
}

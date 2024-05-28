package com.saga.crm.service;

import com.saga.crm.model.Eixo;
import com.saga.crm.model.Porte;
import com.saga.crm.model.Setor;
import com.saga.crm.repositories.EixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EixoService {

    private final EixoRepository eixoRepository;

    @Autowired
    public EixoService(EixoRepository eixoRepository) {
        this.eixoRepository = eixoRepository;
    }

    public List<Eixo> getAllEixos() {
        return eixoRepository.findAll();
    }

    public Eixo getEixoById(Long id) {
        return eixoRepository.findById(id).orElse(null);
    }

    public Eixo findEixoByTitulo(String titulo) {
        return eixoRepository.findByTitulo(titulo);
    }

    public Eixo save(Eixo eixo) {return eixoRepository.save(eixo);
    }
}
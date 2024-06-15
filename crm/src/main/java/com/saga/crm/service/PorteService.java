package com.saga.crm.service;

import com.saga.crm.model.Porte;
import com.saga.crm.model.Setor;
import com.saga.crm.repositories.PorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorteService {

    private final PorteRepository porteRepository;

    @Autowired
    public PorteService(PorteRepository porteRepository) {
        this.porteRepository = porteRepository;
    }

    public List<Porte> getAllPortes() {
        return porteRepository.findAll();
    }

    public Porte getPorteById(Long id) {
        return porteRepository.findById(id).orElse(null);
    }

    public Porte findPorteByTitulo(String titulo) {
        return porteRepository.findPorteByTitulo(titulo);
    }

    public Porte save(Porte porte) {return porteRepository.save(porte);
    }
}

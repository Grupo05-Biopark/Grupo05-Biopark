package com.saga.crm.service;

import com.saga.crm.model.Empresa;
import com.saga.crm.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa cadastrarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }
}

package com.saga.crm.tests;

import com.saga.crm.model.Empresa;
import com.saga.crm.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaTestService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaTestService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    public void save(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public Empresa getEmpresaById(Long id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        return optionalEmpresa.orElse(null);
    }

    public void excluirEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    public boolean cnpjJaCadastrado(String cnpj) {
        return empresaRepository.existsByCnpj(cnpj);
    }
}

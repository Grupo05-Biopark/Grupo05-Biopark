package com.saga.crm.service;

import com.saga.crm.model.Empresa;
import com.saga.crm.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa cadastrarEmpresa(Empresa empresa) {
        validarEmpresa(empresa);
        return empresaRepository.save(empresa);
    }

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public Empresa editarEmpresa(Empresa empresa) {
        validarEmpresa(empresa);
        return empresaRepository.save(empresa);
    }

    public void excluirEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    public boolean cnpjJaCadastrado(String cnpj) {
        return empresaRepository.existsByCnpj(cnpj);
    }

    private void validarEmpresa(Empresa empresa) {
        if (empresa == null ||
                empresa.getNomeFantasia() == null || empresa.getNomeFantasia().isEmpty() ||
                empresa.getCnpj() == null || empresa.getCnpj().isEmpty() ||
                empresa.getRazaoSocial() == null || empresa.getRazaoSocial().isEmpty() ||
                empresa.getLogradouro() == null || empresa.getLogradouro().isEmpty() ||
                empresa.getNumero() == null || empresa.getNumero().isEmpty() ||
                empresa.getCep() == null || empresa.getCep().isEmpty()) {
            throw new CamposObrigatoriosException("Todos os campos são obrigatórios.");
        }

        if (!validarCnpj(empresa.getCnpj())) {
            throw new CNPJInvalidoException("CNPJ inválido.");
        }

        if (cnpjJaCadastrado(empresa.getCnpj())) {
            throw new CNPJJaCadastradoException("Este CNPJ já está cadastrado.");
        }
    }

    private boolean validarCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        return cnpj.length() == 14 && !Pattern.matches("(\\d)\\1{13}", cnpj);
    }

    public static class CamposObrigatoriosException extends RuntimeException {
        public CamposObrigatoriosException(String message) {
            super(message);
        }
    }

    public static class CNPJInvalidoException extends RuntimeException {
        public CNPJInvalidoException(String message) {
            super(message);
        }
    }

    public static class CNPJJaCadastradoException extends RuntimeException {
        public CNPJJaCadastradoException(String message) {
            super(message);
        }
    }
}

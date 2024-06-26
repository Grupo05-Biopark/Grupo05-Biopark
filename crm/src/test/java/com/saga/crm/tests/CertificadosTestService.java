package com.saga.crm.tests;

import com.saga.crm.model.Certificados;
import com.saga.crm.repositories.CertificadosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificadosTestService {
    private final CertificadosRepository certificadosRepository;

    public CertificadosTestService(CertificadosRepository certificadosRepository) {
        this.certificadosRepository = certificadosRepository;
    }

    public void save(Certificados certificados) {
        certificadosRepository.save(certificados);
    }

    public List<Certificados> getAllCertificados(){
        return certificadosRepository.findAll();
    }

    public Certificados findById(Long id){
        return certificadosRepository.findById(id).orElse(null);
    }
}

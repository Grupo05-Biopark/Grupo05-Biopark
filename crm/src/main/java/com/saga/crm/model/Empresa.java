package com.saga.crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeFantasia;
    private String cnpj;
    private String razaoSocial;
    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private Long porteId;
    private Long setorId;

    public Empresa() {

    }

    public Empresa(String nomeFantasia, String cnpj, String razaoSocial, String logradouro, String numero, String cep, String complemento, Long porteId, Long setorId) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.porteId = porteId;
        this.setorId = setorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getPorteId() {
        return porteId;
    }

    public void setPorteId(Long porteId) {
        this.porteId = porteId;
    }

    public Long getSetorId() {
        return setorId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }
}

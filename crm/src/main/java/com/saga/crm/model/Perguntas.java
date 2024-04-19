package com.saga.crm.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Perguntas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @ManyToOne
    private Eixo eixo;

    @ManyToOne
    private Porte porte;

    @ManyToOne
    private Setor setor;

    public Perguntas(Long id, String titulo, String descricao, Eixo eixo, Porte porte, Setor setor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.eixo = eixo;
        this.porte = porte;
        this.setor = setor;
    }

    public Perguntas() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perguntas perguntas = (Perguntas) o;
        return Objects.equals(id, perguntas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Eixo getEixo() {
        return eixo;
    }

    public void setEixo(Eixo eixo) {
        this.eixo = eixo;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}

package com.saga.crm.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Checklist {

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

    private Integer quantidadePerguntas;

    private Integer status = 1;

    @OneToMany(mappedBy = "checklist")
    private Set<FormularioChecklist> formularioChecklists;

    @OneToMany(mappedBy = "checklist")
    private Set<ChecklistPerguntas> checklistPerguntas;

    public Checklist(Long id, String titulo, String descricao, Set<FormularioChecklist> formularioChecklists, Set<ChecklistPerguntas> checklistPerguntas, Eixo eixo, Porte porte, Setor setor, Integer quantidadePerguntas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.formularioChecklists = formularioChecklists;
        this.checklistPerguntas = checklistPerguntas;
        this.eixo = eixo;
        this.porte = porte;
        this.setor = setor;
        this.quantidadePerguntas = quantidadePerguntas;

    }

    public Checklist() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checklist that = (Checklist) o;
        return Objects.equals(id, that.id);
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

    public Set<FormularioChecklist> getFormularioChecklists() {
        return formularioChecklists;
    }

    public void setFormularioChecklists(Set<FormularioChecklist> formularioChecklists) {
        this.formularioChecklists = formularioChecklists;
    }

    public Set<ChecklistPerguntas> getChecklistPerguntas() {
        return checklistPerguntas;
    }

    public void setChecklistPerguntas(Set<ChecklistPerguntas> checklistPerguntas) {
        this.checklistPerguntas = checklistPerguntas;
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

    public Integer getQuantidadePerguntas() {
        return quantidadePerguntas;
    }

    public void setQuantidadePerguntas(Integer quantidadePerguntas) {
        this.quantidadePerguntas = quantidadePerguntas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

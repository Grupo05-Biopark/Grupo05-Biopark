package com.saga.crm.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ChecklistPerguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Checklist checklist;
    @ManyToOne
    private Perguntas perguntas;

    public ChecklistPerguntas() {
    }
    public ChecklistPerguntas(Long id, Checklist checklist, Perguntas perguntas) {
        this.id = id;
        this.checklist = checklist;
        this.perguntas = perguntas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChecklistPerguntas that = (ChecklistPerguntas) o;
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

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public Perguntas getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(Perguntas perguntas) {
        this.perguntas = perguntas;
    }
}

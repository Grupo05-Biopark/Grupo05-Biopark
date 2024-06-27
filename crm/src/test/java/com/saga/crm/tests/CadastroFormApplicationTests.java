package com.saga.crm.tests;

import com.saga.crm.model.Formulario;
import com.saga.crm.model.FormularioChecklist;
import com.saga.crm.service.FormularioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CadastroFormApplicationTests {

    @Autowired
    private FormularioService formularioService;

    @Test
    public void testAdicionarFormulario() {
        // Cenário
        String titulo = "Novo Formulário";
        String descricao = "Descrição do Novo Formulário";

        Set<FormularioChecklist> formularioChecklists = new HashSet<>();

        // Ação
        Formulario formulario = new Formulario();
        formulario.setTitulo(titulo);
        formulario.setDescricao(descricao);
        formulario.setFormularioChecklists(formularioChecklists);

        formularioService.save(formulario);

        // Verificação
        assertNotNull(formulario.getId(), "O ID do formulário não deve ser nulo após salvar");
        assertEquals(titulo, formulario.getTitulo(), "O título do formulário deve ser igual ao especificado");
        assertEquals(descricao, formulario.getDescricao(), "A descrição do formulário deve ser igual à especificada");
        assertEquals(formularioChecklists, formulario.getFormularioChecklists(), "Os formulárioChecklists devem ser iguais aos especificados");
    }
}

package com.saga.crm.tests;

import com.saga.crm.model.Eixo;
import com.saga.crm.model.Perguntas;
import com.saga.crm.model.Porte;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.saga.crm.model.Setor;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class MainApplicationTests {

    @Autowired
    private PerguntasTestService perguntasTestService;

    @Autowired
    private EixoTestService eixoTestService;
    @Autowired
    private SetorTestService setorTestService;
    @Autowired
    private PorteTestService porteTestService;

    @Test
    public void adicionarPergunta() {
//        cenario
        long id = 10L;
        String descricao = "descricao";
        String titulo = "titulo";
        String eixoTitulo = "Ambiental";
        String porteTitulo = "Microempresa";
        String setorTitulo = "T.I";

        Eixo eixo = new Eixo();
        eixo.setId(5L);
        eixo.setTitulo(eixoTitulo);

        Porte porte = new Porte();
        porte.setId(6L);
        porte.setTitulo(porteTitulo);

        Setor setor = new Setor();
        setor.setId(7L);
        setor.setTitulo(setorTitulo);

//        acao
        Perguntas pergunta = new Perguntas();
        pergunta.setId(id);
        pergunta.setDescricao(descricao);
        pergunta.setTitulo(titulo);
        pergunta.setEixo(eixo);
        pergunta.setPorte(porte);
        pergunta.setSetor(setor);

//        verificacao
        assert pergunta.getId().equals(999L);
        assert pergunta.getDescricao().equals(descricao);
        assert pergunta.getTitulo().equals(titulo);
        assert pergunta.getEixo().equals(eixo);
        assert pergunta.getPorte().equals(porte);
        assert pergunta.getSetor().equals(setor);


    }
}
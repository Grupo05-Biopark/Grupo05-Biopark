package com.saga.crm.tests;

import com.saga.crm.model.Empresa;
import com.saga.crm.model.Setor;
import com.saga.crm.model.Porte;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class EmpresaApplicationTests {

    @Autowired
    private EmpresaTestService empresaTestService;

    @Test
    public void adicionarEmpresa() {
        // Cenário
        long id = 2L;
        String nomeFantasia = "Empresa Teste";
        String cnpj = "12345678000100";
        String razaoSocial = "Razão Social Teste";
        String logradouro = "Logradouro Teste";
        String numero = "123";
        String cep = "12345678";

        Setor setor = new Setor();
        setor.setId(3L);
        setor.setTitulo("Tecnologia");

        Porte porte = new Porte();
        porte.setId(3L);
        porte.setTitulo("Pequeno");

        // Ação
        Empresa empresa = new Empresa();
        empresa.setId(id);
        empresa.setNomeFantasia(nomeFantasia);
        empresa.setCnpj(cnpj);
        empresa.setRazaoSocial(razaoSocial);
        empresa.setLogradouro(logradouro);
        empresa.setNumero(numero);
        empresa.setCep(cep);
        empresa.setSetor(setor);
        empresa.setPorte(porte);

        empresaTestService.save(empresa);

        // Verificação
        Empresa empresaSalva = empresaTestService.getEmpresaById(id);
        System.out.println("Empresa salva: " + empresaSalva);
        assert empresaSalva != null;
        assert empresaSalva.getId().equals(id);
        assert empresaSalva.getNomeFantasia().equals(nomeFantasia);
        assert empresaSalva.getCnpj().equals(cnpj);
        assert empresaSalva.getRazaoSocial().equals(razaoSocial);
        assert empresaSalva.getLogradouro().equals(logradouro);
        assert empresaSalva.getNumero().equals(numero);
        assert empresaSalva.getCep().equals(cep);
        assert empresaSalva.getSetor().equals(setor);
        assert empresaSalva.getPorte().equals(porte);
    }


    @Test
    public void atualizarEmpresa() {
        // Cenário
        long id = 1L;
        String nomeFantasia = "Empresa Teste Atualizada";
        String cnpj = "12345678000100";
        String razaoSocial = "Razão Social Teste Atualizada";
        String logradouro = "Logradouro Teste Atualizado";
        String numero = "456";
        String cep = "87654321";

        // Obter a empresa existente
        Empresa empresa = empresaTestService.getEmpresaById(id);

        // Atualizar campos
        empresa.setNomeFantasia(nomeFantasia);
        empresa.setCnpj(cnpj);
        empresa.setRazaoSocial(razaoSocial);
        empresa.setLogradouro(logradouro);
        empresa.setNumero(numero);
        empresa.setCep(cep);

        // Ação
        empresaTestService.save(empresa);

        // Verificação
        Empresa empresaAtualizada = empresaTestService.getEmpresaById(id);
        assert empresaAtualizada != null;
        assert empresaAtualizada.getId().equals(id);
        assert empresaAtualizada.getNomeFantasia().equals(nomeFantasia);
        assert empresaAtualizada.getCnpj().equals(cnpj);
        assert empresaAtualizada.getRazaoSocial().equals(razaoSocial);
        assert empresaAtualizada.getLogradouro().equals(logradouro);
        assert empresaAtualizada.getNumero().equals(numero);
        assert empresaAtualizada.getCep().equals(cep);
    }

    @Test
    public void excluirEmpresa() {
        // Cenário
        long id = 2L;

        // Ação
        empresaTestService.excluirEmpresa(id);

        // Verificação
        Empresa empresaExcluida = empresaTestService.getEmpresaById(id);
        assert empresaExcluida == null;
    }
}

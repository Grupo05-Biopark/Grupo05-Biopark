package com.saga.crm.controller;

import com.saga.crm.model.Empresa;
import com.saga.crm.model.Porte;
import com.saga.crm.model.Setor;
import com.saga.crm.service.EmpresaService;
import com.saga.crm.service.PorteService;
import com.saga.crm.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EmpresaController {

    private final EmpresaService empresaService;
    private final PorteService porteService;
    private final SetorService setorService;

    @Autowired
    public EmpresaController(EmpresaService empresaService, PorteService porteService, SetorService setorService) {
        this.empresaService = empresaService;
        this.porteService = porteService;
        this.setorService = setorService;
    }

    @GetMapping("/empresas")
    public String empresasForm(Model model) {
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresa", new Empresa());
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "empresas/index";
    }

    @PostMapping("/empresas/adicionar")
    public String adicionarEmpresa(Empresa empresa) {
        empresaService.cadastrarEmpresa(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/verificarCnpj")
    public ResponseEntity<Boolean> verificarCnpj(@RequestParam String cnpj) {
        boolean cnpjCadastrado = empresaService.cnpjJaCadastrado(cnpj);
        return ResponseEntity.ok(cnpjCadastrado);
    }

    @GetMapping("/empresas/listar")
    public String showListaEmpresa(Model model) {
        List<Empresa> empresas = empresaService.getAllEmpresas();
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresas", empresas);
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "empresas/list";
    }

    @GetMapping("/empresas/editar/{id}")
    public String editarEmpresa(@PathVariable Long id, Model model) {
        Empresa empresa = empresaService.getEmpresaById(id);
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresa", empresa);
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "empresas/editar";
    }

    @PostMapping("/empresas/editar/{id}")
    public String submitEditarEmpresaForm(@PathVariable Long id, Empresa empresa) {
        empresa.setId(id);
        empresaService.editarEmpresa(empresa);
        return "redirect:/empresas/listar";
    }

    @PostMapping("/empresas/excluir/{id}")
    public String excluirEmpresa(@PathVariable Long id) {
        empresaService.excluirEmpresa(id);
        return "redirect:/empresas/listar";
    }

    @GetMapping("/empresas/porte")
    public ResponseEntity<Map<String, Long>> getEmpresasPorPorte() {
        Map<String, Long> empresasPorPorte = empresaService.getEmpresasPorPorte();
        return ResponseEntity.ok(empresasPorPorte);
    }
    @GetMapping("/empresas/setor")
    public ResponseEntity<Map<String, Long>> getEmpresasPorSetor() {
        Map<String, Long> empresasPorSetor = empresaService.getEmpresasPorSetor();
        return ResponseEntity.ok(empresasPorSetor);
    }
    @GetMapping("/empresas/total")
    public ResponseEntity<Long> getTotalEmpresas() {
        long totalEmpresas = empresaService.getAllEmpresas().size();
        return ResponseEntity.ok(totalEmpresas);
    }

}

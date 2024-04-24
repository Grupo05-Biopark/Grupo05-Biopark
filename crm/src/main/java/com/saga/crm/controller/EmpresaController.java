package com.saga.crm.controller;

import com.saga.crm.model.Empresa;
import com.saga.crm.model.Porte;
import com.saga.crm.model.Setor;
import com.saga.crm.service.EmpresaService;
import com.saga.crm.service.PorteService;
import com.saga.crm.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/empresa")
    public String showEmpresaForm(Model model) {
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresa", new Empresa());
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "empresa";
    }

    @PostMapping("/submitEmpresa")
    public String submitEmpresaForm(Empresa empresa) {
        empresaService.cadastrarEmpresa(empresa);
        return "redirect:/empresa";
    }

    @GetMapping("/listaempresa")
    public String showListaEmpresa(Model model) {
        List<Empresa> empresas = empresaService.getAllEmpresas();
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresas", empresas);
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "listaempresa";
    }

    @GetMapping("/editar/{id}")
    public String showEditarEmpresaForm(@PathVariable Long id, Model model) {
        Empresa empresa = empresaService.getEmpresaById(id);
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresa", empresa);
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "empresa"; // Redireciona para a página de cadastro
    }

    @PostMapping("/editar/{id}")
    public String submitEditarEmpresaForm(@PathVariable Long id, Empresa empresa) {
        empresa.setId(id);
        empresaService.editarEmpresa(empresa);
        return "redirect:/listaempresa";
    }

    @GetMapping("/excluir/{id}")
    public String excluirEmpresa(@PathVariable Long id) {
        empresaService.excluirEmpresa(id);
        return "redirect:/listaempresa";
    }
}

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String empresaForm(Model model) {
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresa", new Empresa());
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "empresa"; 
    }

    @GetMapping("/listaempresa")
    public String listaEmpresa(Model model) {
        List<Empresa> empresa= empresaService.getAllEmpresas();
        List<Porte> portes = porteService.getAllPortes();
        List<Setor> setores = setorService.getAllSetores();

        model.addAttribute("empresa", empresa);
        model.addAttribute("portes", portes);
        model.addAttribute("setores", setores);
        return "listaempresa";
    }

    @PostMapping("/empresa")
    public String submitForm(Empresa empresa) {
        empresaService.cadastrarEmpresa(empresa);
        return "redirect:/empresa";
    }
}

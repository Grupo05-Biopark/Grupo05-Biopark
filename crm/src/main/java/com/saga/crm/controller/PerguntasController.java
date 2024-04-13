package com.saga.crm.controller;

import com.saga.crm.model.Eixo;
import com.saga.crm.model.Perguntas;
import com.saga.crm.model.Porte;
import com.saga.crm.model.Setor;
import com.saga.crm.repositories.PerguntasRepository;
import com.saga.crm.service.EixoService;
import com.saga.crm.service.PerguntasService;
import com.saga.crm.service.PorteService;
import com.saga.crm.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PerguntasController {
    private final PerguntasService perguntasService;
    private final EixoService eixoService;
    private final SetorService setorService;
    private final PorteService porteService;

    @Autowired
    public PerguntasController(PerguntasService perguntasService, EixoService eixoService, SetorService setorService, PorteService porteService) {
        this.perguntasService = perguntasService;
        this.eixoService = eixoService;
        this.setorService = setorService;
        this.porteService = porteService;
    }


    @GetMapping("/perguntas")
    public String perguntasForm(Model model) {
        List<Perguntas> perguntas = perguntasService.getAllPerguntas();
        List<Eixo> eixos = eixoService.getAllEixos();
        List<Setor> setores = setorService.getAllSetores();
        List<Porte> portes = porteService.getAllPortes();


        model.addAttribute("perguntas", perguntas);
        model.addAttribute("eixos", eixos);
        model.addAttribute("setores", setores);
        model.addAttribute("portes", portes);
        return "perguntas";
    }

}

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
import org.springframework.web.bind.annotation.*;

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
        return "perguntas/index";
    }

    @PostMapping("/perguntas/adicionar")
    public String adicionarPergunta(@RequestParam("descricao") String descricao,@RequestParam("titulo") String titulo,@RequestParam("eixo") Long eixoId,
                                    @RequestParam("porte") Long porteId, @RequestParam("setor") String setorId) {

        Perguntas pergunta = new Perguntas();
        pergunta.setDescricao(descricao);
        pergunta.setTitulo(titulo);
        pergunta.setEixo(eixoService.getEixoById(eixoId));
        pergunta.setPorte(porteService.getPorteById(porteId));

        Setor setor = setorService.findSetorByTitulo(setorId);
        if (setor == null) {
            setor = new Setor();
            setor.setTitulo(setorId);
            setorService.save(setor);
            pergunta.setSetor(setor);
        }else {
            pergunta.setSetor(setor);
        }

        perguntasService.save(pergunta);

        return "perguntas/index";
    }

    @GetMapping("/perguntas/editar/{id}")
    public String editarPergunta(@PathVariable Long id, Model model) {
        Perguntas pergunta = perguntasService.getPerguntaById(id);
        List<Eixo> eixos = eixoService.getAllEixos();
        List<Setor> setores = setorService.getAllSetores();
        List<Porte> portes = porteService.getAllPortes();
    
        model.addAttribute("pergunta", pergunta);
        model.addAttribute("eixos", eixos);
        model.addAttribute("setores", setores);
        model.addAttribute("portes", portes);

        return "perguntas/editar";
    }

    @PostMapping("/perguntas/editar/{id}")
    public String atualizarPergunta(@PathVariable Long id, @ModelAttribute Perguntas pergunta) {
        Perguntas perguntaExistente = perguntasService.getPerguntaById(id);
            
        if (perguntaExistente != null) {
            perguntaExistente.setTitulo(pergunta.getTitulo());
            perguntaExistente.setDescricao(pergunta.getDescricao());
            perguntaExistente.setEixo(pergunta.getEixo());
            perguntaExistente.setPorte(pergunta.getPorte());
            perguntaExistente.setSetor(pergunta.getSetor());
    
            perguntasService.save(perguntaExistente);
        }
            
        return "redirect:/perguntas";
    }

    @PostMapping("/perguntas/excluir/{id}")
    public String excluirPergunta(@PathVariable Long id) {
        perguntasService.excluirPergunta(id);
        return "redirect:/perguntas";
    }

    

}
package com.saga.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.crm.model.*;
import com.saga.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChecklistController {

    private final PerguntasService perguntasService;
    private final ChecklistService checklistService;
    private final EixoService eixoService;
    private final SetorService setorService;
    private final PorteService porteService;
    private final ChecklistPerguntasService checklistPerguntasService;

    @Autowired
    public ChecklistController(ChecklistService checklistService, PerguntasService perguntasService, EixoService eixoService, SetorService setorService, PorteService porteService, ChecklistPerguntasService checklistPerguntasService) {
        this.perguntasService = perguntasService;
        this.checklistService = checklistService;
        this.eixoService = eixoService;
        this.setorService = setorService;
        this.porteService = porteService;
        this.checklistPerguntasService = checklistPerguntasService;
    }

    @GetMapping("/checklists")
    public String checklistForm(Model model) {
        List<Perguntas> perguntas = perguntasService.getAllPerguntas();
        List<Eixo> eixos = eixoService.getAllEixos();
        List<Setor> setores = setorService.getAllSetores();
        List<Porte> portes = porteService.getAllPortes();


        model.addAttribute("eixos", eixos);
        model.addAttribute("setores", setores);
        model.addAttribute("portes", portes);

        return "checklists/index";
    }

    @PostMapping("/checklists/adicionar")
    public String adicionarChecklist(@RequestParam("titulo") String titulo,  @RequestParam("descricao") String descricao, @RequestParam("eixo") Long idEixo, @RequestParam("setor") Long idSetor, @RequestParam("porte") Long idPorte, @RequestParam("perguntas") List<Long> idPerguntas) {

        Eixo eixo = eixoService.getEixoById(idEixo);
        Setor setor = setorService.getSetorById(idSetor);
        Porte porte = porteService.getPorteById(idPorte);

        Checklist checklist = new Checklist();
        checklist.setTitulo(titulo);
        checklist.setDescricao(descricao);
        checklist.setEixo(eixo);
        checklist.setSetor(setor);
        checklist.setPorte(porte);
        checklistService.save(checklist);


        Integer quantidadePerguntas = 0;
        for (Long idPergunta : idPerguntas) {
            Perguntas pergunta = perguntasService.getPerguntaById(idPergunta);
            ChecklistPerguntas checklistPerguntas = new ChecklistPerguntas();
            checklistPerguntas.setChecklist(checklist);
            checklistPerguntas.setPerguntas(pergunta);
            checklistPerguntasService.save(checklistPerguntas);
            quantidadePerguntas++;
        }
        checklist.setQuantidadePerguntas(quantidadePerguntas);
        checklistService.save(checklist);

        return "redirect:/checklists";
    }


    @GetMapping("/checklists/filtrar-perguntas")
    public String filtrarPerguntas(Model model, @RequestParam(required = true) Long eixo, @RequestParam(required = true) Long setor, @RequestParam(required = true) Long porte) {
        List<Perguntas> perguntasFiltradas = perguntasService.filtrarPerguntas(eixo, setor, porte);

        model.addAttribute("perguntas", perguntasFiltradas);

        return "checklists/index";
    }

    @GetMapping("/checklists/listar")
    public String listarChecklists(Model model) {
        List<Checklist> checklists = checklistService.getActiveChecklists();
        List<Eixo> eixos = eixoService.getAllEixos();
        List<Setor> setores = setorService.getAllSetores();
        List<Porte> portes = porteService.getAllPortes();

        model.addAttribute("checklists", checklists);
        model.addAttribute("eixos", eixos);
        model.addAttribute("setores", setores);
        model.addAttribute("portes", portes);

        return "checklists/list";
    }


    @PostMapping("/checklists/inativar/{id}")
    public String inativarChecklist(@PathVariable Long id) {
        Checklist checklist = checklistService.getChecklistById(id);
        checklist.setStatus(2);
        checklistService.save(checklist);

        return "redirect:/checklists/listar";
    }


    @GetMapping("/checklists/{id}/perguntas")
    @ResponseBody
    public ResponseEntity<List<Object[]>> getPerguntasByChecklistId(@PathVariable Long id) {
        List<Object[]> perguntasArray = checklistPerguntasService.perguntasByChecklist(id);
        return ResponseEntity.ok(perguntasArray);
    }

    @GetMapping("/checklists/editar/{id}")
    @ResponseBody
    public ResponseEntity<Checklist> editarChecklist(@PathVariable Long id) {
        Checklist checklistTitulosDescricao = checklistService.getChecklistById(id);
        return ResponseEntity.ok(checklistTitulosDescricao);
    }


    @PutMapping("/checklists/editar/{id}")
    @ResponseBody
    public String editarChecklist(@PathVariable Long id, @RequestBody Checklist checklistAtualizado) {
        // Recupere o checklist existente pelo ID
        Checklist checklistExistente = checklistService.getChecklistById(id);

        if (checklistExistente != null) {
            checklistExistente.setTitulo(checklistAtualizado.getTitulo());
            checklistExistente.setDescricao(checklistAtualizado.getDescricao());

            // Salve o checklist atualizado
            checklistService.save(checklistExistente);
        }

        // Redirecione para a página de listagem de checklists
        return "redirect:/checklists";
    }

}

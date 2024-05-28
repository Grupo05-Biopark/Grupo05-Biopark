package com.saga.crm.controller;

import com.saga.crm.model.*;
import com.saga.crm.service.ChecklistService;
import com.saga.crm.service.FormularioChecklistService;
import com.saga.crm.service.FormularioService;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FormularioController {
    private final FormularioService formularioService;
    private final ChecklistService checklistService;

    private final FormularioChecklistService formularioChecklistService;

    public FormularioController(FormularioService formularioService, ChecklistService checklistService, FormularioChecklistService formularioChecklistService) {
        this.formularioService = formularioService;
        this.checklistService = checklistService;
        this.formularioChecklistService = formularioChecklistService;
    }

    @GetMapping("/formulario")
    public String formularioForm(Model model) {
        List<Checklist> ambientalChecklists = checklistService.getChecklistByEixo(1);
        List<Checklist> governancaChecklists = checklistService.getChecklistByEixo(2);
        List<Checklist> socialChecklists = checklistService.getChecklistByEixo(3);

        model.addAttribute("ambientalChecklists", ambientalChecklists);
        model.addAttribute("governancaChecklists", governancaChecklists);
        model.addAttribute("socialChecklists", socialChecklists);

        return "formularios/index";
    }

    @PostMapping("/formulario/adicionar")
    public String adicionarFormulario(@RequestParam("titulo") String titulo,
                                      @RequestParam("descricao") String descricao,
                                      @RequestParam("governancaChecklist") Long governancaChecklistId,
                                      @RequestParam("ambientalChecklist") Long ambientalChecklistId,
                                      @RequestParam("socialChecklist") Long socialChecklistId) {

        Formulario formulario = new Formulario();
        formulario.setTitulo(titulo);
        formulario.setDescricao(descricao);
        formularioService.save(formulario);

        //Salvar o Formulario de Governança
        FormularioChecklist formularioChecklistGovernanca = new FormularioChecklist();
        formularioChecklistGovernanca.setFormulario(formulario);
        formularioChecklistGovernanca.setChecklist(checklistService.getChecklistById(governancaChecklistId));
        formularioChecklistService.save(formularioChecklistGovernanca);

        //Salvar o Formulario de Ambiental
        FormularioChecklist formularioChecklistAmbiental = new FormularioChecklist();
        formularioChecklistAmbiental.setFormulario(formulario);
        formularioChecklistAmbiental.setChecklist(checklistService.getChecklistById(ambientalChecklistId));
        formularioChecklistService.save(formularioChecklistAmbiental);

        //Salvar o Formulario de Social
        FormularioChecklist formularioChecklistSocial = new FormularioChecklist();
        formularioChecklistSocial.setFormulario(formulario);
        formularioChecklistSocial.setChecklist(checklistService.getChecklistById(socialChecklistId));
        formularioChecklistService.save(formularioChecklistSocial);


        return "redirect:/formulario";
    }
}

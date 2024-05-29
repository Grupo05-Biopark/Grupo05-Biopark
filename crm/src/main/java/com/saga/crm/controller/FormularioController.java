package com.saga.crm.controller;

import com.saga.crm.model.*;
import com.saga.crm.service.ChecklistService;
import com.saga.crm.service.EmpresaService;
import com.saga.crm.service.FormularioChecklistService;
import com.saga.crm.service.FormularioService;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FormularioController {
    private final FormularioService formularioService;
    private final ChecklistService checklistService;
    private final EmpresaService empresaService;

    private final FormularioChecklistService formularioChecklistService;

    public FormularioController(FormularioService formularioService, ChecklistService checklistService, FormularioChecklistService formularioChecklistService, EmpresaService empresaService) {
        this.formularioService = formularioService;
        this.checklistService = checklistService;
        this.formularioChecklistService = formularioChecklistService;
        this.empresaService = empresaService;
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
    public String adicionarFormulario(@RequestParam("titulo") String titulo, @RequestParam("descricao") String descricao, @RequestParam("governancaChecklist") Long governancaChecklistId, @RequestParam("ambientalChecklist") Long ambientalChecklistId, @RequestParam("socialChecklist") Long socialChecklistId)  {
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

    @GetMapping("/formulario/listar")
    public String listarFormularios(Model model) {
        List<Formulario> formularios = formularioService.getAllFormulario();

        model.addAttribute("formularios", formularios);
        return "formularios/list";
    }

    @GetMapping("/formulario/{id}/iniciar")
    public String iniciarFormulario(@PathVariable("id") Long id, Model model) {
        Formulario formulario = formularioService.getFormularioById(id);

        List<Empresa> empresas = empresaService.getAllEmpresas();

        // Formatar o CNPJ de cada empresa
        for (Empresa empresa : empresas) {
            empresa.setCnpj(formatCNPJ(empresa.getCnpj()));
        }


        model.addAttribute("formulario", formulario);
        model.addAttribute("empresas", empresas);

        return "formularios/iniciar";
    }
    @GetMapping("/formulario/{id}/iniciar/respostas/{empresaId}")
    public String iniciarFormularioRespostas(@PathVariable("id") Long id, Model model, @PathVariable("empresaId") Long empresaId) {
        Formulario formulario = formularioService.getFormularioById(id);

//        Perguntas Governança
        List<Checklist> governancaChecklistList = checklistService.getChecklistByFormularioIdAndEixo(id, 2);

        Checklist governancaChecklist = null;
        if (!governancaChecklistList.isEmpty()) {
            governancaChecklist = governancaChecklistList.get(0);
        }
        if (governancaChecklist != null) {
            Long governancaChecklistId = governancaChecklist.getId();
            List<Perguntas> governancaPerguntas = checklistService.getChecklistPerguntasById(governancaChecklistId);

            FormularioChecklist formularioChecklistGovernanca = formularioChecklistService.findByFormularioAndChecklist(id, governancaChecklistId);

            System.out.println("FormularioChecklist: " + formularioChecklistGovernanca.getId());

            model.addAttribute("formularioChecklistGovernanca", formularioChecklistGovernanca.getId());
            model.addAttribute("govPerguntas", governancaPerguntas);
        }


//        Perguntas Ambiental
        List<Checklist> ambientalChecklistList = checklistService.getChecklistByFormularioIdAndEixo(id, 1);


        Checklist ambientalChecklist = null;
        if (!ambientalChecklistList.isEmpty()) {
            ambientalChecklist = ambientalChecklistList.get(0);
        }
        if (ambientalChecklist != null) {
            Long ambientalChecklistId = ambientalChecklist.getId();
            List<Perguntas> ambientalPerguntas = checklistService.getChecklistPerguntasById(ambientalChecklistId);

            FormularioChecklist formularioChecklistAmbiental= formularioChecklistService.findByFormularioAndChecklist(id, ambientalChecklistId);

            System.out.println("FormularioChecklist: " + formularioChecklistAmbiental.getId());

            model.addAttribute("formularioChecklistAmbiental", formularioChecklistAmbiental.getId());
            model.addAttribute("ambPerguntas", ambientalPerguntas);
        }

//        Perguntas Social
        List<Checklist> socialChecklistList = checklistService.getChecklistByFormularioIdAndEixo(id, 3);

        Checklist socialChecklist = null;
        if (!socialChecklistList.isEmpty()) {
            socialChecklist = socialChecklistList.get(0);
        }
        if (socialChecklist != null) {
            Long socialChecklistId = socialChecklist.getId();
            List<Perguntas> socialPerguntas = checklistService.getChecklistPerguntasById(socialChecklistId);

            FormularioChecklist formularioChecklistSocial = formularioChecklistService.findByFormularioAndChecklist(id, socialChecklistId);

            System.out.println("FormularioChecklist: " + formularioChecklistSocial.getId());

            model.addAttribute("formularioChecklistSocial", formularioChecklistSocial.getId());
            model.addAttribute("socPerguntas", socialPerguntas);
        }

        model.addAttribute("empresaId", empresaId);
        model.addAttribute("formulario", formulario);



        return "formularios/respostas";
    }

    @PostMapping("/formulario/{id}/iniciar/respostas/{empresaId}/salvar")
    public String salvarRespostas(@PathVariable("id") Long id, @PathVariable("empresaId") Long empresaId, @RequestBody List<List<Integer>> respostas) {

        System.out.println(respostas);
        throw new RuntimeException("Stopping code for debugging");

//        return "redirect:/formulario";
    }

    public static String formatCNPJ(String cnpj) {
        return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d+)", "$1.$2.$3/$4-$5");
    }

}

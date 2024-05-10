package com.saga.crm.controller;

import com.saga.crm.model.*;
import com.saga.crm.service.FormularioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FormularioController {
    private final FormularioService formularioService;

    public FormularioController(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    @GetMapping("/formulario")
    public String formularioForm(Model model) {
        List<Formulario> formularios = formularioService.getAllFormulario();

        return "formularios/index";
    }
}

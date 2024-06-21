package com.saga.crm.controller;

import com.saga.crm.model.Checklist;
import com.saga.crm.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    private final ChecklistService checklistService;

    @Autowired
    public DashboardController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Checklist> checklists = checklistService.getAllChecklists();

        Integer contagemChecklists = 0;

        for (Checklist checklist : checklists) {
            contagemChecklists++;
        }

        model.addAttribute("contagemChecklists", contagemChecklists);

        return "dashboard";
    }
}
package com.saga.crm.controller;

import com.saga.crm.model.Certificados;
import com.saga.crm.model.Checklist;
import com.saga.crm.service.CertificadosService;
import com.saga.crm.service.ChecklistService;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class CertificadosController {

    private final CertificadosService certificadosService;
    private final ChecklistService checklistService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    public CertificadosController(CertificadosService certificadosService, ChecklistService checklistService){
        this.certificadosService = certificadosService;
        this.checklistService = checklistService;
    }

    @GetMapping("/certificados/index")
    public String certificadosIndex(Model model){

        List<Certificados> certificados = certificadosService.getAllCertificados();

        model.addAttribute("certificados", certificados);


        return "certificados/index";
    }

    @GetMapping("/certificados/{id}/emitir")
    @ResponseBody
    public void emitirCertificado(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Certificados certificado = certificadosService.findById(id);

        Long formularioId = certificado.getFormulario().getId();

        List<Checklist> ambientalChecklist = checklistService.getChecklistByFormularioIdAndEixo(formularioId, 1);
        List<Checklist> governancaChecklist = checklistService.getChecklistByFormularioIdAndEixo(formularioId, 2);
        List<Checklist> socialChecklist = checklistService.getChecklistByFormularioIdAndEixo(formularioId, 3);


        Context context = new Context();
        context.setVariable("certificado", certificado);
        context.setVariable("ambientalChecklist", ambientalChecklist);
        context.setVariable("governancaChecklist", governancaChecklist);
        context.setVariable("socialChecklist", socialChecklist);

        String html = templateEngine.process("certificados/certificado", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes()), target);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=certificado.pdf");
        response.getOutputStream().write(target.toByteArray());
    }


}

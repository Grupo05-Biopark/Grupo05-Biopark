package com.saga.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saga.crm.model.Usuario;
import com.saga.crm.repositories.UsuarioRepository;

@Controller
public class EsquecerSenhaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/esqueci-senha")
    public String exibirFormularioEsquecerSenha() {
        return "esqueci-senha";
    }

    @PostMapping("/esqueci-senha")
    @ResponseBody
    public ResponseEntity<Map<String, String>> enviarEmailRedefinicaoSenha(@RequestParam String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Map<String, String> response = new HashMap<>();
        if (usuario != null) {
            // Lógica para enviar e-mail de redefinição de senha
            // Aqui você pode chamar um serviço para enviar um e-mail com um link de
            // redefinição de senha
            response.put("message", "E-mail de redefinição de senha enviado com sucesso!");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "O e-mail fornecido não está associado a nenhuma conta.");
            return ResponseEntity.status(404).body(response);
        }
    }
}

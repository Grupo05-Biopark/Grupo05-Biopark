package com.saga.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saga.crm.model.Usuario;
import com.saga.crm.repositories.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> loginSubmit(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Map<String, String> response = new HashMap<>();
        if (usuario != null && bCryptPasswordEncoder.matches(senha, usuario.getSenha())) {
            response.put("message", "Login realizado com sucesso!");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Falha ao realizar login. Verifique suas credenciais e tente novamente.");
            return ResponseEntity.status(401).body(response);
        }
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha, RedirectAttributes attributes) {
        try {
            if (usuarioRepository.findByEmail(email) != null) {
                attributes.addFlashAttribute("error", "Email já cadastrado.");
                return "redirect:/cadastro";
            }

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(bCryptPasswordEncoder.encode(senha));
            usuarioRepository.save(usuario);

            return "redirect:/login";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao tentar cadastrar usuário. Coloque credenciais válidas.");
            return "redirect:/cadastro";
        }
    }
}

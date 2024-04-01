package com.saga.crm.controller;

import com.saga.crm.model.Usuario;
import com.saga.crm.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String senha) {
        if (usuarioRepository.findByEmail(email).getSenha().equals(senha)) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
        if (usuarioRepository.findByEmail(email) != null) {
            return "redirect:/cadastro";
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);

        return "redirect:/login";
    }
}

package com.saga.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saga.crm.model.Usuario;
import com.saga.crm.repositories.UsuarioRepository;

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
    public String loginSubmit(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && bCryptPasswordEncoder.matches(senha, usuario.getSenha())) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha, RedirectAttributes attributes) {
        try {
            if (usuarioRepository.findByEmail(email) != null) {
                return "redirect:/cadastro";
            }

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(bCryptPasswordEncoder.encode(senha));
            usuarioRepository.save(usuario);

            return "redirect:/login";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Erro ao tentar cadastrar usuário.\nColoque credenciais válidas.");
            return "redirect:/cadastro";
        }
    }
}

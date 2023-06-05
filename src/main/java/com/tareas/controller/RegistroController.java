package com.tareas.controller;

import com.tareas.data.UsuarioRepository;
import com.tareas.modelo.FormularioRegistro;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistroController {

    private UsuarioRepository usuariosRepo;
    private PasswordEncoder passwordEncoder;

    public RegistroController( UsuarioRepository usuariosRepo, PasswordEncoder passwordEncoder) {
        this.usuariosRepo = usuariosRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registroForm() {
        return "registro";
    }

    @PostMapping
    public String procesarRegistro(FormularioRegistro formulario) {
        usuariosRepo.save(formulario.toUsuario(passwordEncoder));
        return "redirect:/login";
    }
}

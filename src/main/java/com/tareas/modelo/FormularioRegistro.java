package com.tareas.modelo;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class FormularioRegistro {

    private String username;
    private String password;

    public Usuario toUsuario(PasswordEncoder passwordEncoder) {
        return new Usuario(username, passwordEncoder.encode(password));
    }
}

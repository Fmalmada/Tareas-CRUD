package com.tareas.Service;

import com.tareas.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tareas.modelo.Usuario;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private UsuarioRepository usuariosRepo;

    @Autowired
    public UserDetailsServiceImp(UsuarioRepository usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario unUsuario = usuariosRepo.findByUsername(username);

        if (unUsuario != null) return unUsuario;

        throw new UsernameNotFoundException( "Usuario " + username + " no encontrado");
    }
}

package com.tareas.data;

import com.tareas.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}

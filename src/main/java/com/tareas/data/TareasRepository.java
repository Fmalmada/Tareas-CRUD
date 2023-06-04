package com.tareas.data;

import com.tareas.modelo.Tarea;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface TareasRepository extends JpaRepositoryImplementation<Tarea, Long> {
}

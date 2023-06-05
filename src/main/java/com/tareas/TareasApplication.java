package com.tareas;

import com.tareas.data.TareasRepository;
import com.tareas.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    @Autowired
    TareasRepository tareasRepo;

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tarea unaTarea = new Tarea();
        unaTarea.setNombre("Prueba 1");

        Tarea otraTarea = new Tarea();
        otraTarea.setNombre("Prueba 2");
        tareasRepo.save(unaTarea);
        tareasRepo.save(otraTarea);
    }


}

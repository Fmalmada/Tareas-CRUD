package com.tareas.controller;

import com.tareas.data.TareasRepository;
import com.tareas.modelo.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class TareasController {

    private TareasRepository tareasRepo;

    @Autowired
    public TareasController(TareasRepository unTareasRepo) {
        tareasRepo = unTareasRepo;
    }

    @GetMapping("/")
    public String verTareas(Model unModelo){
        unModelo.addAttribute("tareas", tareasRepo.findAll());
        return "home";
    }

    @GetMapping("/nuevaTarea")
    public String nuevaTarea(Model unModelo) {
        Tarea nuevaTarea = new Tarea();
        unModelo.addAttribute("tarea", nuevaTarea);
        return "nuevaTarea";
    }

    @PostMapping("/nuevaTarea")
    public String guardarTarea(@Validated Tarea tareaNueva, BindingResult resultado, Model unModelo) {
        if (resultado.hasErrors()) {
            unModelo.addAttribute("tarea", tareaNueva);
            return "nuevaTarea";
        }
        tareasRepo.save(tareaNueva);
        return "redirect:/";
    }



    @GetMapping("/editar/{id}")
    public String editarTarea(@PathVariable("id") Long id, Model modelo) {
        Optional<Tarea> posibleTarea = tareasRepo.findById(id);
        if (posibleTarea.isEmpty()){
            throw new IllegalArgumentException("Tarea " + id + "no encontrada");
        }

        modelo.addAttribute("tarea", posibleTarea.get());
        return "nuevaTarea";
    }

    @PostMapping("/editar/{id}")
    public String guardarTareaEditada(@Validated Tarea tareaEditada, BindingResult resultado, @PathVariable("id") Long id, Model unModelo) {
        Optional<Tarea> posibleTarea = tareasRepo.findById(id);
        if (posibleTarea.isEmpty()){
            throw new IllegalArgumentException("Tarea " + id + "no encontrada");
        }

        if (resultado.hasErrors()) {
            unModelo.addAttribute("tarea", tareaEditada);
            return "nuevaTarea";
        }

        Tarea unaTarea = posibleTarea.get();
        unaTarea.setNombre(tareaEditada.getNombre());
        tareasRepo.save(unaTarea);
        return "redirect:/";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id, Model unModelo) {
       Optional<Tarea> posibleTarea = tareasRepo.findById(id);
        if (posibleTarea.isEmpty()){
            throw new IllegalArgumentException("Tarea " + id + "no encontrada");
        }
        tareasRepo.deleteById(id);
        return "redirect:/";
    }
}


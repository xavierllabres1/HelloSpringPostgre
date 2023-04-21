package com.world.hello.controllers;

import com.world.hello.models.Nombre;
import com.world.hello.services.NombreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NombreController {

    @Autowired
    NombreService nombreService;

    @GetMapping("/")
    public String findAllNombres(Model model){
        model.addAttribute("namelist", nombreService.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String newNombreForm(Model model){
        model.addAttribute("nombreForm", new Nombre());
        return "form";
    }

    @PostMapping("/new/submit")
    public String newNombreSubmit(@ModelAttribute("nombreForm") Nombre nombre){
        nombreService.save(nombre);
        return"redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editNombreForm(@PathVariable long id, Model model){
        Nombre nombre = nombreService.findByID(id);
        if(nombre != null){
            model.addAttribute("nombreForm", nombre);
            return "form";
        } else {
            return "redirect:/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editNombreSubmit (@ModelAttribute("nombreForm") Nombre nombre){
        nombreService.save(nombre);
        return "redirect:/";
    }

    @PostMapping("/edit/delete")
    public String editNombreDelete(@ModelAttribute("nombreForm")Nombre nombre){
        nombreService.delete(nombre);
        return "redirect:/";
    }

}

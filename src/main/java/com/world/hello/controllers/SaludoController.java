package com.world.hello.controllers;

import com.world.hello.models.Nombre;
import com.world.hello.services.NombreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SaludoController {

    @Autowired
    NombreService nombreService;

    @GetMapping("/")
    public String findNoms(Model model){
        model.addAttribute("namelist", nombreService.findAll());
        return "index";
    }

}

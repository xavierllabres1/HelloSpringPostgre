package com.world.hello.controllers;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import com.world.hello.services.NameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NameController {

    final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/")
    public String findAllNames(Model model){
        model.addAttribute("nameList", nameService.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String newNameForm(Model model){
        model.addAttribute("nameForm", new NameView.Builder().setId(0L).build());
        return "form";
    }

    @PostMapping("/new/submit")
    public String newNameSubmit(@ModelAttribute("nameForm") Name.Builder builder){
//        NameView nameView = builder.build();
        Name nameView = builder.build();
        nameService.save(nameView);
        return"redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editNameForm(@PathVariable Long id, Model model){
        //NameView nameView = nameService.findById(id);       // Aquí no hi ha builder???
        Name nameView = nameService.findById(id);       // Aquí no hi ha builder???
        if(nameView != null){
            model.addAttribute("nameForm", nameView);
            return "form";
        } else {
            return "redirect:/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editNameSubmit(@ModelAttribute("nameForm") Name.Builder builder){
        Name nameView = builder.build();
        //NameView nameView = builder.build();
        nameService.save(nameView);
        return "redirect:/";
    }

    @PostMapping("/edit/delete")
    public String editNameDelete(@ModelAttribute("nameForm") Name.Builder builder){
        Name nameView = builder.build();
        //NameView nameView = builder.build();
        nameService.delete(nameView);
        return "redirect:/";
    }

}

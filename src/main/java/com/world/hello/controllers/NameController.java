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
import org.springframework.web.bind.annotation.RequestBody;

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
        model.addAttribute("nameForm", NameView.builder().id(0).build());
        return "form";
    }

    @PostMapping("/new/submit")
    public String newNameSubmit(@RequestBody final NameView nameForm){
        nameService.save(nameForm);
        return"redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editNameForm(@PathVariable final Long id, Model model){
        NameView nameView = nameService.findById(id);
        if(nameView != null){
            model.addAttribute("nameForm", nameView);
            return "form";
        } else {
            return "redirect:/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editNameSubmit(@RequestBody final NameView nameView){
        nameService.save(nameView);
        return "redirect:/";
    }

    @PostMapping("/edit/delete")
    public String editNameDelete(@RequestBody final NameView nameView){
        nameService.delete(nameView);
        return "redirect:/";
    }

}

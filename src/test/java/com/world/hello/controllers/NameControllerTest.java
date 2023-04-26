package com.world.hello.controllers;

import com.world.hello.models.Name;
import com.world.hello.repository.NameRepository;
import com.world.hello.services.NameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NameControllerTest {

    // SUT
    NameController nameController;

    // Dependencies
    @Mock
    NameService nameService;

    @Mock
    NameRepository nameRepository;

    @Mock
    Model model;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        nameController = new NameController(nameService);
    }

    @Test
    @DisplayName("Controller - GET - Find All Names")
    void findAllNames(){
//        when(nameService.findAll()).thenReturn(List.of());
        String view = nameController.findAllNames(model);

        //JUnit
        assertEquals("index", view);

        //Mockito
        verify(model, times(1)).addAttribute(anyString(), any());
        verify(nameService, times(1)).findAll();
    }

    @Test
    @DisplayName("Controller - GET - New Name Form")
    void newNameForm() {
        String view = nameController.newNameForm(model);

        //JUnit
        assertEquals("form", view);

        //Mockito
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    @DisplayName("Controller - POST - New Name Submit")
    void newNameSubmit() {
        Name.Builder nameBuilder = new Name.Builder();

        doNothing().when(nameService).save(any(Name.class));
        String view = nameController.newNameSubmit(nameBuilder);

        //JUnit
        assertEquals("redirect:/", view);

        //Mockito
        verify(nameService, times(1)).save(any(Name.class));
    }


    @Test
    @DisplayName("Controller - GET - Edit Name Form")
    void editNameForm() {


        //JUnit
        assertEquals("form", view);

        //Mockito
        verify(model, times(1)).addAttribute(anyString(), any());
    }


    @Test
    @DisplayName("Controller - POST - Edit Name Submit")
    void editNameSubmit() {
    }

    @Test
    @DisplayName("Controller - POST - Edit Name Delete")
    void editNameDelete() {
    }

}
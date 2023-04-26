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


import java.util.List;
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
    Model model;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        nameController = new NameController(nameService);
    }

    @Test
    @DisplayName("Controller - GET - Find All Names")
    void findAllNames(){
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
        Name name = new Name.Builder().setId(1L).setFirstName("Jhon").build();
        when(nameService.findById(1L)).thenReturn(name);
        String view = nameController.editNameForm(1L,model);

        //JUnit
        assertEquals("form", view);
        //Mockito
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    @DisplayName("Controller - GET - Edit NotFoundName Form")
    void editNotFoundNameForm() {
        when(nameService.findById(1L)).thenReturn(null);
        String view = nameController.editNameForm(1L,model);

        //JUnit
        assertEquals("redirect:/new", view);
    }


    @Test
    @DisplayName("Controller - POST - Edit Name Submit")
    void editNameSubmit() {
        Name.Builder nameBuilder = new Name.Builder();
        String view = nameController.editNameSubmit(nameBuilder);

        //JUnit
        assertEquals("redirect:/", view);
        assertNotNull(nameBuilder);
        //Mockito
        verify(nameService, times(1)).save(any(Name.class));
    }

    @Test
    @DisplayName("Controller - POST - Edit Name Delete")
    void editNameDelete() {
        Name.Builder nameBuilder = new Name.Builder();
        String view = nameController.editNameDelete(nameBuilder);

        //JUnit
        assertEquals("redirect:/", view);
        assertNotNull(nameBuilder);
        //Mockito
        verify(nameService, times(1)).delete(any(Name.class));
    }

}
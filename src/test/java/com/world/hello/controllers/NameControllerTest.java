package com.world.hello.controllers;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import com.world.hello.repository.NameRepository;
import com.world.hello.services.NameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NameControllerTest {

    // Dependencies
    @Mock
    NameService nameService;

    // SUT
    @InjectMocks
    NameController nameController;

    @Mock
    Model model;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
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
        NameView.Builder nameBuilder = new NameView.Builder();

        doNothing().when(nameService).save(any(NameView.class));
        String view = nameController.newNameSubmit(nameBuilder);

        //JUnit
        assertEquals("redirect:/", view);
        //Mockito
        verify(nameService, times(1)).save(any(NameView.class));
    }

    @Test
    @DisplayName("Controller - GET - Edit Name Form")
    void editNameForm() {
        NameView nameView = new NameView.Builder()
                .setId(1)
                .setFirstName("Jhon")
                .build();
        when(nameService.findById(1L)).thenReturn(nameView);
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
        NameView.Builder nameViewBuilder = new NameView.Builder();
        String view = nameController.editNameSubmit(nameViewBuilder);

        //JUnit
        assertEquals("redirect:/", view);
        assertNotNull(nameViewBuilder);
        //Mockito
        verify(nameService, times(1)).save(any(NameView.class));
    }

    @Test
    @DisplayName("Controller - POST - Edit Name Delete")
    void editNameDelete() {
        NameView.Builder nameViewBuilder = new NameView.Builder();
        String view = nameController.editNameDelete(nameViewBuilder);

        //JUnit
        assertEquals("redirect:/", view);
        assertNotNull(nameViewBuilder);
        //Mockito
        verify(nameService, times(1)).delete(any(NameView.class));
    }

}
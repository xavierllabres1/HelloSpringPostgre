package com.world.hello.controllers;

import com.world.hello.models.NameView;
import com.world.hello.services.NameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
        NameView nameView = NameView.builder().build();

        doNothing().when(nameService).save(any(NameView.class));
        String view = nameController.newNameSubmit(nameView);

        //JUnit
        assertEquals("redirect:/", view);
        //Mockito
        verify(nameService, times(1)).save(any(NameView.class));
    }

    @Test
    @DisplayName("Controller - GET - Edit Name Form")
    void editNameForm() {
        NameView nameView = NameView.builder()
                .id(1)
                .firstName("Jhon")
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
        final NameView nameView = NameView.builder()
                .firstName("Devon")
                .lastName("Xavi")
                .build();
        final String view = nameController.editNameSubmit(nameView);

        //JUnit
        assertEquals("redirect:/", view);
        //Mockito
        verify(nameService, times(1)).save(nameView);
    }

    @Test
    @DisplayName("Controller - POST - Edit Name Delete")
    void editNameDelete() {
        NameView nameViewBuilder = NameView.builder().build();
        String view = nameController.editNameDelete(nameViewBuilder);

        //JUnit
        assertEquals("redirect:/", view);
        assertNotNull(nameViewBuilder);
        //Mockito
        verify(nameService, times(1)).delete(any(NameView.class));
    }

}

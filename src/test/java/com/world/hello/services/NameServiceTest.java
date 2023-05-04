package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import com.world.hello.repository.NameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NameServiceTest {

    // Dependencies
    @Mock
    NameRepository nameRepository;

    // SUT
    @InjectMocks
    NameService nameService;

    // Instanciar el Mock
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

//    @BeforeEach
//    void setUpByConstructor(){
//        nameRepository = mock(NameRepository.class);
//        nameService = new NameService(nameRepository);
//    }


    @Test
    @DisplayName("Service - Find All Not EmptyList")
    void findAllNotEmpty() {
        when(nameRepository.findAll()).thenReturn(List.of(new Name.Builder().setId(1L).setFirstName("Jhon").build()));
        List<NameView> names = nameService.findAll();

        //JUnit
        assertNotNull(names);
        assertEquals(1, names.size());
        //Mockito
        verify(nameRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Service - Find All EmptyList")
    void findAllEmpty() {
        when(nameRepository.findAll()).thenReturn(List.of());
        List<NameView> names = nameService.findAll();

        //JUnit
        assertNotNull(names);
        assertEquals(0, names.size());
        //Mockito
        verify(nameRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Service - Find All Null")
    void findAllNull() {
        when(nameRepository.findAll()).thenReturn(null);
        List<NameView> names = nameService.findAll();

        //JUnit
        assertNull(names);
        //Mockito
        verify(nameRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Service - Find by Id Found")
    void findByIDFound() {
        when(nameRepository.findById(anyLong())).thenReturn(Optional.of(new Name.Builder().setId(1L).setFirstName("Jhon").build()));
        NameView nameView = nameService.findById(1L);

        //JUnit
        assertNotNull(nameView);
        assertEquals("Jhon", nameView.getFirstName());
        //Mockito
        verify(nameRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Service - Find by Id Not Found")
    void findByIDNotFound() {
        when(nameRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        NameView nameView = nameService.findById(anyLong());

        //JUnit
        assertNull(nameView);
        //Mockito
        verify(nameRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Service - Save")
    void save() {
        NameView nameView = new NameView.Builder().setId(1).setFirstName("Jhon").build();
        nameService.save(nameView);

        //Mockito
        verify(nameRepository, times(1)).save(any(Name.class));
    }

    @Test
    @DisplayName("Service - Delete")
    void delete() {
        NameView nameView = new NameView.Builder().setId(1).setFirstName("Jhon").build();
        nameService.delete(nameView);

        //Mockito
        verify(nameRepository, times(1)).delete(any(Name.class));
    }

}
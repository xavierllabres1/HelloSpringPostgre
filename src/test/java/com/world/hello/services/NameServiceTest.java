package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.repository.NameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NameServiceTest {

    // SUT
    NameService nameService;

    // Dependencies
    @Mock
    NameRepository nameRepository;

    // Instanciar el Mock
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        nameService = new NameService(nameRepository);
    }

    @Test
    @DisplayName("Service - Find All Not EmptyList")
    void findAllNotEmpty() {
        when(nameRepository.findAll()).thenReturn(List.of(new Name.Builder().setId(1L).setFirstName("Jhon").build()));
        List<Name> names = nameService.findAll();

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
        List<Name> names = nameService.findAll();

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
        List<Name> names = nameService.findAll();

        //JUnit
        assertNull(names);
        //Mockito
        verify(nameRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Service - Find by Id Found")
    void findByIDFound() {
        when(nameRepository.findById(anyLong())).thenReturn(Optional.of(new Name.Builder().setId(1L).setFirstName("Jhon").build()));
        Name name = nameService.findById(1L);

        //JUnit
        assertNotNull(name);
        assertEquals("Jhon", name.getFirstName());
        //Mockito
        verify(nameRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Service - Find by Id Not Found")
    void findByIDNotFound() {
        when(nameRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        Name name = nameService.findById(anyLong());

        //JUnit
        assertNull(name);
        //Mockito
        verify(nameRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Service - Save")
    void save() {
        Name name = new Name.Builder().setId(1L).setFirstName("Jhon").build();
        nameService.save(name);

        //Mockito
        verify(nameRepository, times(1)).save(name);
    }
    @Test
    @DisplayName("Service - Delete")
    void delete() {
        Name name = new Name.Builder().setId(1L).setFirstName("Jhon").build();
        nameService.delete(name);

        //Mockito
        verify(nameRepository, times(1)).delete(name);
    }

}
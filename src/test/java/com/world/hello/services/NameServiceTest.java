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
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NameServiceTest {

    // Dependencies
    @Mock
    NameRepository nameRepository;
    @Mock
    ConversionService conversionService;

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
        when(nameRepository.findAll()).thenReturn(List.of(Name.builder().id(1L).firstName("Jhon").build()));
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
        when(conversionService.convert(any(Name.class), (Class<NameView>)any())).thenReturn(((Class<NameView>)any()));
        List<NameView> names = nameService.findAll();

        //JUnit
        assertNotNull(names);
        assertEquals(0, names.size());
        //Mockito
        verify(nameRepository, times(1)).findAll();
    }



    @Test
    @DisplayName("Service - Find by Id Found")
    void findByIDFound() {
        Optional<Name> name = Optional.of(Name.builder().id(1L).firstName("Jhon").build());
        NameView nameView = NameView.builder().id(1).firstName("Jhon").build();
        when(nameRepository.findById(1L)).thenReturn(name);
        when(conversionService.convert(any(Name.class), (Class<NameView>)any())).thenReturn(nameView);
        NameView nameViewResult = nameService.findById(1L);

        //JUnit
        assertNotNull(nameViewResult);
        assertEquals(nameView.getFirstName(), nameViewResult.getFirstName());
        //Mockito
        verify(nameRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Service - Find by Id Not Found")
    void findByIDNotFound() {
        when(nameRepository.findById(anyLong())).thenReturn(Optional.empty());
        NameView nameView = nameService.findById(anyLong());

        //JUnit
        assertNull(nameView);
        //Mockito
        verify(nameRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Service - Save")
    void save() {
        NameView nameView = NameView.builder()
                .id(1)
                .firstName("Jhon")
                .lastName("Smith")
                .build();
        nameService.save(nameView);

        //Mockito
        verify(nameRepository, times(1)).save(conversionService.convert(nameView, Name.class));
    }

    @Test
    @DisplayName("Service - Delete")
    void delete() {
        NameView nameView = NameView.builder().id(1).firstName("Jhon").build();
        nameService.delete(nameView);

        //Mockito
        verify(nameRepository, times(1)).delete(conversionService.convert(nameView, Name.class));
    }

}

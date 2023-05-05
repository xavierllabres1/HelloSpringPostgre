package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NameViewConverterTest {

    @Mock
    ConversionService conversionService;

    // Instanciar el Mock
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("Service - Name to NameView")
    void testConverterNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";
        String lastName = "Smith";

        Name name = Name.builder()
                .id(id)
                .firstName((firstName + " " + lastName).trim())
                .build();

        NameView nameViewInstance = NameView.builder()
                .id(id.intValue())
                .firstName(firstName)
                .lastName(lastName)
                .build();

        when(conversionService.convert(any(Name.class), (Class<NameView>)any())).thenReturn(nameViewInstance);

        NameView nameViewResult = conversionService.convert(name, NameView.class);

        assertEquals(nameViewResult.getId().toString(), name.getId().toString());
        assertEquals((nameViewResult.getFirstName() + " " + nameViewResult.getLastName()).trim(), name.getFirstName());
    }
    @Test
    @DisplayName("Service - Name to NameView (No LastName)")
    void testConverterNotLastNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";
        String lastName = "";

        Name name = Name.builder()
                .id(id)
                .firstName((firstName + " " + lastName).trim())
                .build();

        NameView nameViewInstance = NameView.builder()
                .id(id.intValue())
                .firstName(firstName)
                .lastName(lastName)
                .build();

        when(conversionService.convert(any(Name.class), (Class<NameView>)any())).thenReturn(nameViewInstance);

        NameView nameViewResult = conversionService.convert(name, NameView.class);

        assertEquals(nameViewResult.getId().toString(), name.getId().toString());
        assertEquals((nameViewResult.getFirstName() + " " + nameViewResult.getLastName()).trim(), name.getFirstName());
    }
    @Test
    @DisplayName("Service - Name to NameView (Empty)")
    void testConverterEmptyToNameView(){

        Long id = 0L;
        String firstName = "";
        String lastName = "";

        Name name = Name.builder()
                .id(id)
                .firstName((firstName + " " + lastName).trim())
                .build();

        NameView nameViewInstance = NameView.builder()
                .id(id.intValue())
                .firstName(firstName)
                .lastName(lastName)
                .build();

        when(conversionService.convert(any(Name.class), (Class<NameView>)any())).thenReturn(nameViewInstance);

        NameView nameViewResult = conversionService.convert(name, NameView.class);

        assertEquals(nameViewResult.getId().toString(), name.getId().toString());
        assertEquals((nameViewResult.getFirstName() + " " + nameViewResult.getLastName()).trim(), name.getFirstName());
    }

}

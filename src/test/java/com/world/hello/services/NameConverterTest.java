package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class NameConverterTest {

    @Mock
    ConversionService conversionService;

    // Instanciar el Mock
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Service - NameView to Name ")
    void testConverterNameViewToName(){

        Integer id = 1;
        String firstName = "Jhon";
        String lastName = "Smith";

        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Name nameInstance = Name.builder()
                .id(id.longValue())
                .firstName((firstName + " " + lastName).trim())
                .build();

        when(conversionService.convert(any(NameView.class), (Class<Name>)any())).thenReturn(nameInstance);

        Name nameResult = conversionService.convert(nameView, Name.class);

        assertEquals(nameResult.getId().toString(), nameView.getId().toString());
        assertEquals(nameResult.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }

    @Test
    @DisplayName("Service - NameView to Name (No LastName)")
    void testConverterNameViewToNameNoLastName(){

        Integer id = 1;
        String firstName = "Jhon";
        String lastName = "";

        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Name nameInstance = Name.builder()
                .id(id.longValue())
                .firstName((firstName + " " + lastName).trim())
                .build();

        when(conversionService.convert(any(NameView.class), (Class<Name>)any())).thenReturn(nameInstance);

        Name nameResult = conversionService.convert(nameView, Name.class);

        assertEquals(nameResult.getId().toString(), nameView.getId().toString());
        assertEquals(nameResult.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }
    @Test
    @DisplayName("Service - NameView to Name (Empty)")
    void testConverterNameViewToNameEmpty(){

        Integer id = 0;
        String firstName = "";
        String lastName = "";

        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Name nameInstance = Name.builder()
                .id(id.longValue())
                .firstName((firstName + " " + lastName).trim())
                .build();

        when(conversionService.convert(any(NameView.class), (Class<Name>)any())).thenReturn(nameInstance);

        Name nameResult = conversionService.convert(nameView, Name.class);

        assertEquals(nameResult.getId().toString(), nameView.getId().toString());
        assertEquals(nameResult.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }

}

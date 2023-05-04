package com.world.hello.services;

import com.fasterxml.classmate.util.ClassStack;
import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.convert.ConversionService;

import static org.junit.jupiter.api.Assertions.*;

class NameConverterTest {

    private ConversionService conversionService;

    @Test
    @DisplayName("Converter recibe un objeto tipo Name y lo pasa a NameView")
    void testConverterNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";
        String lastName = "Smith";

        Name name = Name.builder()
                .id(id)
                .firstName(firstName + " " + lastName)
                .build();

        NameView nameView = conversionService.convert(name, NameView.class);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(),
                (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }

    @Test
    @DisplayName("Converter recibe un objeto tipo NameView y lo pasa a Name")
    void testConverterNameViewToName(){

        Integer id = 1;
        String firstName = "Jhon";
        String lastName = "Smith";

        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Name name = conversionService.convert(nameView, Name.class);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }


}

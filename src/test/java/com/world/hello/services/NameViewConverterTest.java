package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NameViewConverterTest {

    NameViewConverter nameViewConverter = new NameViewConverter();

    @Test
    @DisplayName("Service - Name to NameView")
    void testConverterNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";
        String lastName = "Smith";

        Name name = Name.builder()
                .id(id)
                .firstName(firstName + " " + lastName)
                .build();

        NameView nameView = nameViewConverter.convert(name);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }
    @Test
    @DisplayName("Service - Not lastName Name to NameView")
    void testConverterNotLastNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";
        String lastName = "";

        Name name = Name.builder()
                .id(id)
                .firstName(firstName + " " + lastName)
                .build();

        NameView nameView = nameViewConverter.convert(name);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }
    @Test
    @DisplayName("Service - Empty Name to NameView")
    void testConverterEmptyToNameView(){

        Long id = 0L;
        String firstName = "";
        String lastName = "";

        Name name = Name.builder()
                .id(id)
                .firstName(firstName + " " + lastName)
                .build();

        NameView nameView = nameViewConverter.convert(name);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }

}

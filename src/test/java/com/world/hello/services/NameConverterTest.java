package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameConverterTest {

    NameConverter nameConverter = new NameConverter();

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

        Name name = nameConverter.convert(nameView);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }

    @Test
    @DisplayName("Service - NameView to Name No LastName")
    void testConverterNameViewToNameNoLastName(){

        Integer id = 1;
        String firstName = "Jhon";
        String lastName = "";

        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Name name = nameConverter.convert(nameView);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(),
                (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }
    @Test
    @DisplayName("Service - NameView to Name Empty")
    void testConverterNameViewToNameEmpty(){

        Integer id = 0;
        String firstName = "";
        String lastName = "";

        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Name name = nameConverter.convert(nameView);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(),
                (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }

}

package com.world.hello.services;

import com.fasterxml.classmate.util.ClassStack;
import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameConverterTest {

    @Test
    @DisplayName("Converter recibe un objeto tipo Name y lo pasa a NameView")
    void testConverterNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";
        String lastName = "Smith";

        Name name = new Name.Builder()
                .setId(id)
                .setFirstName(firstName + " " + lastName)
                .build();

        NameView nameView = NameConverter.converterToNameView(name);

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

        NameView nameView = new NameView.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();

        Name name = NameConverter.converterToName(nameView);

        assertEquals(name.getId().toString(), nameView.getId().toString());
        assertEquals(name.getFirstName(), (nameView.getFirstName() + " " + nameView.getLastName()).trim());
    }


}
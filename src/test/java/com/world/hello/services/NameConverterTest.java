package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameConverterTest {


    NameConverter nameConverter;

    @Test
    @DisplayName("Converter recibe un objeto tipo Name y lo pasa a NameView")
    void testConverterNameToNameView(){

        Long id = 1L;
        String firstName = "Jhon";

        Name name = new Name.Builder()
                .setId(id)
                .setFirstName(firstName)
                .build();

        NameView nameView = NameConverter.converterToNameView(name);

        assertEquals(name.getId(), nameView.getId());
        assertEquals(name.getFirstName(), nameView.getFirstName());
    }

    @Test
    @DisplayName("Converter recibe un objeto tipo NameView y lo pasa a Name")
    void testConverterNameViewToName(){

        Long id = 1L;
        String firstName = "Jhon";

        NameView nameView = new NameView.Builder()
                .setId(id)
                .setFirstName(firstName)
                .build();

        Name name = NameConverter.converterToName(nameView);

        assertEquals(name.getId(), nameView.getId());
        assertEquals(name.getFirstName(), nameView.getFirstName());
    }


}
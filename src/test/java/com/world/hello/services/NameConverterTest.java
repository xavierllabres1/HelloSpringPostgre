package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameConverter;
import com.world.hello.models.NameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameConverterTest {


    NameConverter nameConverter;

    @Test
    @DisplayName("Converter recibe un objeto tipo Name y lo pasa a NameView")
    void converterNameToNameView(){
        /*
        when(nameConverter.converter(new Name.Builder()
                        .setId(1L)
                        .setFirstName("Carlos")
                        .build()))
                .thenReturn(new NameView.Builder()
                        .setId(1L)
                        .setFirstName("Carlos")
                        .build());
*/
        NameView x = nameConverter.converterToNameView(new Name.Builder()
                .setId(1L)
                .setFirstName("Carlos")
                .build());
        assertEquals(x, new NameView.Builder()
                .setId(1L)
                .setFirstName("Carlos")
                .build());
        assertInstanceOf(NameView.class, x);
        //verify(nameConverter, times(1)).converter(any());
    }

    @Test
    @DisplayName("Converter recibe un objeto tipo NameView y lo pasa a Name")
    void converterNameViewToName(){
        Name x = nameConverter.converterToName(new NameView.Builder()
                .setId(1L)
                .setFirstName("Carlos")
                .build());
        assertEquals(x, new Name.Builder()
                .setId(1L)
                .setFirstName("Carlos")
                .build());
        assertInstanceOf(Name.class, x);
        //verify(nameConverter, times(1)).converter(any());
    }
}
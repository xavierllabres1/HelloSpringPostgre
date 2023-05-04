package com.world.hello.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NameViewTest {

    @Test
    @DisplayName("Model - Constructor Test")
    void testConstructor() {
        Integer id = 1;
        String firstName = "John";
        String lastName = "Smith";
        NameView nameView = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        assertEquals(id, nameView.getId());
        assertEquals(firstName, nameView.getFirstName());
        assertEquals(lastName, nameView.getLastName());
    }

    @Test
    @DisplayName("Model - Builder Test")
    void testBuilder() {
        Integer id = 1;
        String firstName = "John";
        String lastName = "Smith";
        NameView.NameViewBuilder builder = NameView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName);
        NameView nameView1 = builder.build();
        NameView nameView2 = builder.build();
        assertNotSame(nameView1, nameView2);
    }

}

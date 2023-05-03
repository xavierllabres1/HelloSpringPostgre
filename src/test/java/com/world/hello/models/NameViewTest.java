package com.world.hello.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameViewTest {

    @Test
    @DisplayName("Model - Constructor Test")
    void testConstructor() {
        Integer id = 1;
        String firstName = "John";
        String lastName = "Smith";
        NameView nameView = new NameView.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
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
        NameView.Builder builder = new NameView.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName);
        NameView nameView1 = builder.build();
        NameView nameView2 = builder.build();
        assertNotSame(nameView1, nameView2);
    }

}
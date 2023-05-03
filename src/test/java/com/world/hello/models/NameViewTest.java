package com.world.hello.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameViewTest {

    @Test
    @DisplayName("Model - Constructor Test")
    void testConstructor() {
        Long id = 1L;
        String firstName = "John";
        NameView nameView = new NameView.Builder()
                .setId(id)
                .setFirstName(firstName)
                .build();
        assertEquals(id, nameView.getId());
        assertEquals(firstName, nameView.getFirstName());
    }

    @Test
    @DisplayName("Model - Builder Test")
    void testBuilder() {
        Long id = 1L;
        String firstName = "John";
        NameView.Builder builder = new NameView.Builder()
                .setId(id)
                .setFirstName(firstName);
        NameView nameView1 = builder.build();
        NameView nameView2 = builder.build();
        assertNotSame(nameView1, nameView2);
    }

}
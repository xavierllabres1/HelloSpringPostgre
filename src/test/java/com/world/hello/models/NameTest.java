package com.world.hello.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameTest {

    @Test
    @DisplayName("Model Name - Constructor Test")
    void testConstructor() {
        Long id = 1L;
        String firstName = "John";
        Name name = new Name.Builder()
                .setId(id)
                .setFirstName(firstName)
                .build();
        assertEquals(id, name.getId());
        assertEquals(firstName, name.getFirstName());
    }

    @Test
    @DisplayName("Model Name - Builder Test")
    void testBuilder() {
        Long id = 1L;
        String firstName = "John";
        Name.Builder builder = new Name.Builder()
                .setId(id)
                .setFirstName(firstName);
        Name name1 = builder.build();
        Name name2 = builder.build();
        assertNotSame(name1, name2);
    }
}
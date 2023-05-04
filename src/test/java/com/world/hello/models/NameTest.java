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
        Name name = Name.builder()
                .id(id)
                .firstName(firstName)
                .build();
        assertEquals(id, name.getId());
        assertEquals(firstName, name.getFirstName());
    }

    @Test
    @DisplayName("Model Name - Builder Test")
    void testBuilder() {
        Long id = 1L;
        String firstName = "John";
        Name.NameBuilder builder = Name.builder()
                .id(id)
                .firstName(firstName);
        Name name1 = builder.build();
        Name name2 = builder.build();
        assertNotSame(name1, name2);
    }
}
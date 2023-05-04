package com.world.hello.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder(setterPrefix = "")
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = Name.NameBuilder.class)
@Table(name = "names", schema = "public")
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "firstname")
    String firstName;

    public Name(){
        id = 1L;
        firstName = "";
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class NameBuilder {
    }
}

package com.world.hello.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = NameView.NameViewBuilder.class)
public class NameView {

    Integer id;
    String firstName;
    String lastName;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NameViewBuilder {
    }
}

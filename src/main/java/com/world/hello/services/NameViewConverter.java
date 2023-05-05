package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// Source: Name -> Destination: NameView
@Component
public class NameViewConverter implements Converter<Name, NameView> {

    @Override
    public NameView convert(final Name name) {

        return NameView.builder()
                .id(name.getId().intValue())
                .firstName(Stream.of(name.getFirstName().split(" "))
                            .findFirst()
                            .orElse(""))
                .lastName(Stream.of(name.getFirstName().split(" "))
                            .skip(1)
                            .collect(Collectors.joining(" ")))
                .build();
    }
}


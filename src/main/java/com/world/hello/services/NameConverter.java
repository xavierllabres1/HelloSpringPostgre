package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

// Source: NameView -> Destination: Name
@Component
public class NameConverter implements Converter<NameView, Name> {

    @Override
    public Name convert(final NameView nameView) {

        return Name.builder()
                .id(nameView.getId().longValue())
                .firstName((nameView.getFirstName() + " " + nameView.getLastName()).trim())
                .build();
    }
}


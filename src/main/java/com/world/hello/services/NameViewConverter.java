package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NameViewConverter implements Converter<Name, NameView> {

    @Override
    public NameView convert(final Name name) {

        String [] fullName = name.getFirstName().split(" ");
        String firstName = "";
        String lastName = "";

        if (fullName.length == 1){
            firstName = fullName[0];
        } else {
            firstName = fullName[0];
            for (int i = 1; i < fullName.length; i++){
                lastName = lastName + fullName[i] + " ";
            }
        }
//        final String firstName = Stream.of(name.getFirstName().split(" "))
//                .findFirst()
//                .orElse("");
//
//        final String lastName = Stream.of(name.getFirstName().split(" "))
//                .skip(1)
//                .collect(Collectors.joining(" "));

        return NameView.builder()
                .id(name.getId().intValue())
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}


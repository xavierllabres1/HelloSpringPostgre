package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class NameConverter implements Converter<Name, NameView> {

    @Override
    public NameView convert(Name name) {

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

        return NameView.builder()
                .id(name.getId().intValue())
                .firstName(firstName)
                .lastName(lastName.trim())
                .build();
    }
}


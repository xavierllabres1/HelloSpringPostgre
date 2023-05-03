package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.stereotype.Component;

public class NameConverter {

    public static NameView converterToNameView(Name name) {

        String [] fullName = name.getFirstName().split(" ");
        String firstName = "";
        String lastName = "";

        if (fullName.length < 2 && fullName.length >= 1){
            firstName = fullName[0];
        } else {
            firstName = fullName[0];
            for (int i = 1; i < fullName.length; i++){
                lastName = lastName + fullName[i] + " ";
            }
        }

        return new NameView.Builder()
                .setId((Integer)name.getId().intValue())
                .setFirstName(firstName)
                .setLastName(lastName.trim())
                .build();
    }

    public static Name converterToName(NameView nameView) {
        return new Name.Builder()
                .setId((Long)nameView.getId().longValue())
                .setFirstName((nameView.getFirstName() + " " + nameView.getLastName()).trim())
                .build();
    }
}

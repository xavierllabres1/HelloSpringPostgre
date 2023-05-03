package com.world.hello.services;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.stereotype.Component;

public class NameConverter {

    public static NameView converterToNameView(Name object) {
            return new NameView.Builder()
                    .setId(((Name) object).getId())
                    .setFirstName(((Name) object).getFirstName())
                    .build();

    }

    public static Name converterToName(NameView object) {
        return new Name.Builder()
                .setId(((NameView) object).getId())
                .setFirstName(((NameView) object).getFirstName())
                .build();

    }
}

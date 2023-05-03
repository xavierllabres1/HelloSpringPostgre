package com.world.hello.models;

import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import org.springframework.stereotype.Component;

public class NameConverter {

    public NameView converterToNameView(Name object) {
            return new NameView.Builder()
                    .setId(((Name) object).getId())
                    .setFirstName(((Name) object).getFirstName())
                    .build();

    }

    public Name converterToName(NameView object) {
        return new Name.Builder()
                .setId(((NameView) object).getId())
                .setFirstName(((NameView) object).getFirstName())
                .build();

    }
}

package com.world.hello.models;

import javax.persistence.Column;

public class NameView {

    private Long id;

    private String firstName;

    public NameView(){}

    private NameView(NameView.Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
    }


    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public static class Builder {

        private Long id;
        private String firstName;

        public Builder(){}

        public NameView.Builder setId(Long id){
            this.id = id;
            return this;
        }

        public NameView.Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public NameView build(){
            return new NameView(this);
        }

    }
}

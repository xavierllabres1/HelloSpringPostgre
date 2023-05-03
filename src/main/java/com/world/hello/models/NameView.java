package com.world.hello.models;

public class NameView {

    private Integer id;

    private String firstName;
    private String lastName;

    public NameView(){}

    private NameView(NameView.Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }


    public Integer getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder {

        private Integer id;
        private String firstName;
        private String lastName;

        public Builder(){}

        public NameView.Builder setId(Integer id){
            this.id = id;
            return this;
        }

        public NameView.Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public NameView.Builder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public NameView build(){
            return new NameView(this);
        }

    }
}

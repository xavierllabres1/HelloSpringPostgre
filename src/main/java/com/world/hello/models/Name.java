package com.world.hello.models;

import javax.persistence.*;

@Entity
@Table(name = "nombres")
public final class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String firstName;


    private Name(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
    }


    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public static class Builder{

        private Long id;
        private String firstName;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Name build(){
            return new Name(this);
        }

    }


}

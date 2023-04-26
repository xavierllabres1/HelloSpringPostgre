package com.world.hello.models;

import javax.persistence.*;

@Entity
@Table(name = "names", schema = "public")
public final class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    public Name(){}

    private Name(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
    }

    public Name(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
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

        public Builder setId(Long id){
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Name build(){
            return new Name(this);
        }

    }


}

package com.world.hello.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder(setterPrefix = "set", toBuilder = true) // No deberia ser "with"??
@NoArgsConstructor()
@AllArgsConstructor
@Table(name = "names", schema = "public")
public final class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

}

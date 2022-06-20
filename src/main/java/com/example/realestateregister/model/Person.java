package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "firstname is mandatory")
    String firstname;
    @NotBlank(message = "surname is mandatory")
    String surname;
    @NotBlank(message = "address is mandatory")
    String address;
    @Pattern(regexp = "^[\\d]{4,20}$", message = "only type numbers")
    @Column(name = "phone_number")
    String phoneNumber;
    //    @NotNull
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "rolePersonReference")
    @JsonDeserialize(as = List.class)
    List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

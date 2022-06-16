package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @NotNull
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "firstname is mandatory")
    String firstname;
    @NotBlank(message = "surname is mandatory")
    String surname;
    @NotBlank(message = "address is mandatory")
    String address;
    @Pattern(regexp = "^[\\d]{4,10}$", message = "only type numbers")
    @Column(name = "phone_number")
    String phoneNumber;
    @NotNull
    @OneToMany(mappedBy = "person")
    @JsonManagedReference
    List<Role> roles;
}

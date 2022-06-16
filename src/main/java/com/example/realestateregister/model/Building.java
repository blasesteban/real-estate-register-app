package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "building")
public class Building {
    @NotNull
//    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Positive
    @Column(name = "square_meters", nullable = false)
    long squareMeters;
    @Positive
    @Column(nullable = false)
    long price;
    //   @NotNull
    @OneToMany(mappedBy = "building")
//    @JsonManagedReference
    @JsonIgnore
    List<Room> rooms;
    //  @NotNull
    @OneToMany(mappedBy = "building")
//    @JsonManagedReference
    @JsonIgnore
    List<Role> roles;
}

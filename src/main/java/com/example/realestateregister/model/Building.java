package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "building")
public class Building {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Positive
    @Column(name = "square_meters", nullable = false)
    long squareMeters;
    @Positive
    @Column(nullable = false)
    long price;
    //    @NotNull
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "roomBuildingReference")
    @JsonDeserialize(as = List.class)
    List<Room> rooms;
    //    @NotNull
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "roleBuildingReference")
    @JsonDeserialize(as = List.class)
    List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return id == building.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull
    @Column(name = "role_type")
    RoleType roleType;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "person", referencedColumnName = "id")
    @JsonBackReference
    Person person;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "building", referencedColumnName = "id")
    @JsonBackReference
    Building building;
}

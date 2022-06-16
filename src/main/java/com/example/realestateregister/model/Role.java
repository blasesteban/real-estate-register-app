package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull
    @Column(name = "role_type")
    RoleType roleType;
    //    @NotNull
    @ManyToOne()
    @JoinColumn(name = "person", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_roles_person"))
//    @JsonBackReference
    @JsonIgnore
    Person person;
    //    @NotNull
    @ManyToOne()
    @JoinColumn(name = "building", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_roles_building"))
//    @JsonBackReference
    @JsonIgnore
    Building building;
}

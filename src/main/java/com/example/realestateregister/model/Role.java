package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    @Enumerated(EnumType.ORDINAL)
    RoleType roleType;
    //    @NotNull
    @ManyToOne()
    @JoinColumn(name = "person", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_roles_person"))
    @JsonBackReference(value = "rolePersonReference")
    Person person;
    //    @NotNull
    @ManyToOne()
    @JoinColumn(name = "building", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_roles_building"))
    @JsonBackReference(value = "roleBuildingReference")
    Building building;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && roleType == role.roleType && Objects.equals(person, role.person) && Objects.equals(building, role.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

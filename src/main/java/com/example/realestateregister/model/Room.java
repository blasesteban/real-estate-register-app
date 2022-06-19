package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull
    @Column(name = "room_type")
    @Enumerated(EnumType.ORDINAL)
    RoomType roomType;
    @Positive @Min(message = "must be at least 1 m2", value = 1)
    @Column(nullable = false)
    long size;
    //    @NotNull
    @ManyToOne()
    @JoinColumn(name = "building", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_rooms_building"))
    @JsonBackReference(value = "roomBuildingReference")
    Building building;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && size == room.size && roomType == room.roomType && Objects.equals(building, room.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

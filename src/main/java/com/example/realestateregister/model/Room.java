package com.example.realestateregister.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @NotNull
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull
    @Column(name = "room_type")
    RoomType roomType;
    @Positive @Min(message = "must be at least 1 m2", value = 1)
    @Column(nullable = false)
    long size;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "building", referencedColumnName = "id")
    @JsonBackReference
    Building building;
}

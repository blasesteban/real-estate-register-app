package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {
    @NotNull
    @Column(name = "room_type")
    String roomType;
    @Positive @Min(message = "must be at least 1 m2", value = 1)
    @Column(nullable = false)
    long size;
}

package com.example.realestateregister.dto;

import com.example.realestateregister.model.RoomType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Data
public class RoomDto {
    @NotNull
    @Column(name = "room_type")
    RoomType roomType;
    @Positive @Min(message = "must be at least 1 m2", value = 1)
    @Column(nullable = false)
    long size;
}

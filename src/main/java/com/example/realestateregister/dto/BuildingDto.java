package com.example.realestateregister.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuildingDto {
    @Positive
    @Column(name = "square_meters", nullable = false)
    long squareMeters;
    @Positive
    @Column(nullable = false)
    long price;
}

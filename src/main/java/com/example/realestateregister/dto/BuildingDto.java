package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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

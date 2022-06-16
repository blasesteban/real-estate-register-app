package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class BuildingRoomDto {
    @NotNull
    private Long buildingId;
    @NotNull
    private Long roomId;
}

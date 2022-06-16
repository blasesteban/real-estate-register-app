package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class BuildingRoleDto {
    @NotNull
    private Long buildingId;
    @NotNull
    private Long roleId;
}

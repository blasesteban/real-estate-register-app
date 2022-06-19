package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuildingRoleDto {
    @NotNull
    private Long buildingId;
    @NotNull
    private Long roleId;
}

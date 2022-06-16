package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class PersonRoleDto {
    @NotNull
    private Long personId;
    @NotNull
    private Long roleId;
}

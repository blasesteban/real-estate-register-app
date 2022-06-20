package com.example.realestateregister.dto;

import com.example.realestateregister.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {
    @NotNull
    @Column(name = "role_type")
    RoleType roleType;
}

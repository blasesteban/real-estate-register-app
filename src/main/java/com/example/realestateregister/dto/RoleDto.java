package com.example.realestateregister.dto;

import com.example.realestateregister.model.RoleType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class RoleDto {
    @NotNull
    @Column(name = "role_type")
    RoleType roleType;
}

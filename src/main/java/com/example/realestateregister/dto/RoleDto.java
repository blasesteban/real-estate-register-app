package com.example.realestateregister.dto;

import com.example.realestateregister.model.RoleType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Data
public class RoleDto {
    @Column(name = "role_type")
    RoleType roleType;
}

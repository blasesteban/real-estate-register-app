package com.example.realestateregister.dto;

import com.example.realestateregister.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonBuildingDto {
    String firstname;
    String surname;
    String address;
    String phoneNumber;
    RoleType roleType;
}

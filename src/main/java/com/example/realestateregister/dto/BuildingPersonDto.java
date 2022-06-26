package com.example.realestateregister.dto;

import com.example.realestateregister.model.RoleType;
import com.example.realestateregister.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuildingPersonDto {
    long squareMeters;
    long price;
    List<Room> rooms;
    RoleType roleType;
}

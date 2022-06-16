package com.example.realestateregister.controller;

import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.BuildingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingControllerTest {

    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingService buildingService;
    private final Room room1 = new Room();
    private final Room room2 = new Room();
    private final Room room3 = new Room();

    private final Role role1 = new Role();
    private final Role role2 = new Role();
    private final Role role3 = new Role();

    private final Building building1 = new Building(1, 20, 25, List.of(room1), List.of(role1));
    private final Building building2 = new Building(1, 50, 70, List.of(room1, room2), List.of(role1, role2));
    private final Building building3 = new Building(1, 100, 150, List.of(room1, room2, room3), List.of(role2, role3));
    private final List<Building> buildingList = List.of(building1, building2, building3);

    @Test
    void listBuildings() {
        when(buildingService.listBuildings()).thenReturn(buildingList);
        List<Building> expected = buildingController.listBuildings();
        assertEquals(expected, buildingList);
        assertEquals(expected.get(0), building1);
        assertEquals(expected.get(1), building2);
        assertEquals(expected.get(2), building3);
    }

    @Test
    void getBuildingById() {
        when(buildingService.getBuildingById(1)).thenReturn(building1);
        Building expected = buildingController.getBuildingById(1);
        assertEquals(expected, building1);
        assertEquals(expected.getRooms(), building1.getRooms());
    }

/*    @Test
    void addBuildingAndReturnId() {
        when(buildingService.addBuildingAndReturnId(new BuildingDto(building1.getSquareMeters(), building1.getPrice()))).thenReturn(building1.getId());
        Long expected = buildingController.addBuildingAndReturnId(new BuildingDto(building1.getSquareMeters(), building1.getPrice()));
        assertEquals(expected, building1.getId());
    }*/

    @Test
    void updateBuildingById() {

    }

    @Test
    void deleteBuildingById() {
    }

    @Test
    void addRoomToBuilding() {
    }

    @Test
    void listRoomsByBuildingId() {
        when(buildingService.listRoomsByBuildingId(building1.getId())).thenReturn(building1.getRooms());
        List<Room> expected1 = buildingController.listRoomsByBuildingId(building1.getId());
        assertEquals(expected1, building1.getRooms());
        when(buildingService.listRoomsByBuildingId(building2.getId())).thenReturn(building2.getRooms());
        List<Room> expected2 = buildingController.listRoomsByBuildingId(building2.getId());
        assertEquals(expected2, building2.getRooms());
        when(buildingService.listRoomsByBuildingId(building3.getId())).thenReturn(building3.getRooms());
        List<Room> expected3 = buildingController.listRoomsByBuildingId(building3.getId());
        assertEquals(expected3, building3.getRooms());
    }

    @Test
    void addRoleToBuilding() {
    }

    @Test
    void listRolesByBuildingId() {
        when(buildingService.listRolesByBuildingId(building1.getId())).thenReturn(building1.getRoles());
        List<Role> expected1 = buildingController.listRolesByBuildingId(building1.getId());
        assertEquals(expected1, building1.getRoles());
        when(buildingService.listRolesByBuildingId(building2.getId())).thenReturn(building2.getRoles());
        List<Role> expected2 = buildingController.listRolesByBuildingId(building2.getId());
        assertEquals(expected2, building2.getRoles());
        when(buildingService.listRolesByBuildingId(building3.getId())).thenReturn(building3.getRoles());
        List<Role> expected3 = buildingController.listRolesByBuildingId(building3.getId());
        assertEquals(expected3, building3.getRoles());
    }
}
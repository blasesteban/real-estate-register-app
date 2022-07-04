package com.example.realestateregister.controller;

import com.example.realestateregister.dto.BuildingDto;
import com.example.realestateregister.dto.BuildingRoleDto;
import com.example.realestateregister.dto.BuildingRoomDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.BuildingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingControllerTest {
    @InjectMocks
    private BuildingController buildingController;
    @Mock
    private BindingResult br;
    @Mock
    private BuildingService buildingService;

    private final Room room1 = new Room();
    private final Room room2 = new Room();
    private final Room room3 = new Room();

    private final Role role1 = new Role();
    private final Role role2 = new Role();
    private final Role role3 = new Role();

    private final Building building1 = new Building(1, 20, 25, List.of(room1), List.of(role1));
    private final Building building2 = new Building(2, 50, 70, List.of(room1, room2), List.of(role1, role2));
    private final Building building3 = new Building(3, 100, 150, List.of(room1, room2, room3), List.of(role2, role3));
    private final List<Building> buildingList = List.of(building1, building2, building3);

    private BuildingDto buildingDtoFromEntity(Building building) {
        return new BuildingDto(building.getSquareMeters(), building.getPrice());
    }

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

    @Test
    void addBuildingAndReturnId() {
        when(br.hasErrors()).thenReturn(false);
        when(buildingService.addBuildingAndReturnId(buildingDtoFromEntity(building1))).thenReturn(building1.getId());
        ResponseEntity<?> expected = buildingController.addBuildingAndReturnId(buildingDtoFromEntity(building1), br);
        assertEquals(expected.getBody(), building1.getId());
    }

    @Test
    void addBuildingAndReturnIdWrong() {
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = buildingController.addBuildingAndReturnId(new BuildingDto(), br);
        assertEquals(expected.getBody(), "invalid building");
    }

    @Test
    void updateBuildingById() {
        when(br.hasErrors()).thenReturn(false);
        ResponseEntity<?> expected = buildingController.updateBuildingById(buildingDtoFromEntity(building1), building1.getId(), br);
        verify(buildingService).updateBuildingById(buildingDtoFromEntity(building1), building1.getId());
        assertEquals(expected.getBody(), "building is updated");
    }

    @Test
    void updateBuildingByIdWrong() {
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = buildingController.updateBuildingById(new BuildingDto(), building1.getId(), br);
        assertEquals(expected.getBody(), "invalid building");
    }

    @Test
    void deleteBuildingById() {
        buildingController.deleteBuildingById(building1.getId());
        verify(buildingService).deleteBuildingById(building1.getId());
    }

    @Test
    void addRoomToBuilding() {
        BuildingRoomDto buildingRoomDto = new BuildingRoomDto(building1.getId(), room1.getId());
        when(br.hasErrors()).thenReturn(false);
        buildingController.addRoomToBuilding(buildingRoomDto, br);
        verify(buildingService).addRoomToBuilding(buildingRoomDto);
    }

    @Test
    void addRoomToBuildingWrong() {
        BuildingRoomDto buildingRoomDto = new BuildingRoomDto(new Building().getId(), new Room().getId());
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = buildingController.addRoomToBuilding(buildingRoomDto, br);
        assertEquals(expected.getBody(), "invalid room id or building id");
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
        BuildingRoleDto buildingRoleDto = new BuildingRoleDto(building1.getId(), role1.getId());
        when(br.hasErrors()).thenReturn(false);
        buildingController.addRoleToBuilding(buildingRoleDto, br);
        verify(buildingService).addRoleToBuilding(buildingRoleDto);
    }

    @Test
    void addRoleToBuildingWrong() {
        BuildingRoleDto buildingRoleDto = new BuildingRoleDto(new Building().getId(), new Role().getId());
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = buildingController.addRoleToBuilding(buildingRoleDto, br);
        assertEquals(expected.getBody(), "invalid role id or building id");
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
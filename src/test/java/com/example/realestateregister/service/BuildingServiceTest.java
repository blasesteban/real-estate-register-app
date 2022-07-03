package com.example.realestateregister.service;

import com.example.realestateregister.dao.BuildingJpaDao;
import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dao.RoomJpaDao;
import com.example.realestateregister.dto.BuildingDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {
    @InjectMocks
    private BuildingService buildingService;
    @Mock
    private BuildingJpaDao buildingJpaDao;
    @Mock
    private RoomJpaDao roomJpaDao;
    @Mock
    private RoleJpaDao roleJpaDao;

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

    private BuildingDto buildingDtoFromEntity(Building building) {
        return new BuildingDto(building.getSquareMeters(), building.getPrice());
    }

    @Test
    void listBuildings() {
        when(buildingJpaDao.findAll()).thenReturn(buildingList);
        List<Building> expected = buildingService.listBuildings();
        assertEquals(expected, buildingList);
        assertEquals(expected.get(0), building1);
        assertEquals(expected.get(1), building2);
        assertEquals(expected.get(2), building3);
    }

    @Test
    void getBuildingById() {
        when(buildingJpaDao.findById(1L)).thenReturn(Optional.of(building1));
        Building expected = buildingService.getBuildingById(1);
        assertEquals(expected, building1);
        assertEquals(expected.getRooms(), building1.getRooms());
        assertEquals(expected.getRoles(), building1.getRoles());
    }

/*    @Test
    void addBuildingAndReturnId() {
    }*/

    @Test
    void updateBuildingById() {
        when(buildingJpaDao.findById(building1.getId())).thenReturn(Optional.of(building1));
        buildingService.updateBuildingById(buildingDtoFromEntity(building1), building1.getId());
        verify(buildingJpaDao).save(building1);
    }

    @Test
    void deleteBuildingById() {
        buildingService.deleteBuildingById(building1.getId());
        verify(buildingJpaDao).deleteById(building1.getId());
    }

/*    @Test
    void addRoomToBuilding() {
    }*/

    @Test
    void listRoomsByBuildingId() {
        when(roomJpaDao.listRoomsByBuildingId(building1.getId())).thenReturn(building1.getRooms());
        List<Room> expected1 = buildingService.listRoomsByBuildingId(building1.getId());
        assertEquals(expected1, building1.getRooms());
        when(roomJpaDao.listRoomsByBuildingId(building2.getId())).thenReturn(building2.getRooms());
        List<Room> expected2 = buildingService.listRoomsByBuildingId(building2.getId());
        assertEquals(expected2, building2.getRooms());
        when(roomJpaDao.listRoomsByBuildingId(building3.getId())).thenReturn(building3.getRooms());
        List<Room> expected3 = buildingService.listRoomsByBuildingId(building3.getId());
        assertEquals(expected3, building3.getRooms());
    }

/*    @Test
    void addRoleToBuilding() {
    }*/

    @Test
    void listRolesByBuildingId() {
        when(roleJpaDao.listRolesByBuildingId(building1.getId())).thenReturn(building1.getRoles());
        List<Role> expected1 = buildingService.listRolesByBuildingId(building1.getId());
        assertEquals(expected1, building1.getRoles());
        when(roleJpaDao.listRolesByBuildingId(building2.getId())).thenReturn(building2.getRoles());
        List<Role> expected2 = buildingService.listRolesByBuildingId(building2.getId());
        assertEquals(expected2, building2.getRoles());
        when(roleJpaDao.listRolesByBuildingId(building3.getId())).thenReturn(building3.getRoles());
        List<Role> expected3 = buildingService.listRolesByBuildingId(building3.getId());
        assertEquals(expected3, building3.getRoles());
    }
}
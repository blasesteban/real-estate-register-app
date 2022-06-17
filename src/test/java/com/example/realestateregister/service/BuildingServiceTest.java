package com.example.realestateregister.service;

import com.example.realestateregister.controller.BuildingController;
import com.example.realestateregister.dao.BuildingJpaDao;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {
    @InjectMocks
    private BuildingService buildingService;
    @Mock
    private BuildingJpaDao buildingJpaDao;

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
        when(buildingJpaDao.findAll()).thenReturn(buildingList);
        List<Building> expected = buildingService.listBuildings();
        assertEquals(expected, buildingList);
        assertEquals(expected.get(0), building1);
        assertEquals(expected.get(1), building2);
        assertEquals(expected.get(2), building3);
    }

    @Test
    void getBuildingById() {
    }

    @Test
    void addBuildingAndReturnId() {
    }

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
    }

    @Test
    void addRoleToBuilding() {
    }

    @Test
    void listRolesByBuildingId() {
    }
}
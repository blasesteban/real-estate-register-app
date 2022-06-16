package com.example.realestateregister.service;

import com.example.realestateregister.dao.BuildingJpaDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
/*

@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;
}
*/

@RunWith(SpringRunner.class)
class BuildingServiceTest {
/*
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public BuildingService buildingService() {
            return new BuildingServiceImpl();
        }
    }
*/

    @Autowired
    private BuildingService buildingService;

    @MockBean
    private BuildingJpaDao buildingJpaDao;

    @Test
    void listBuildings() {
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
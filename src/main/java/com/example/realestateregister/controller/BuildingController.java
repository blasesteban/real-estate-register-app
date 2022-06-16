package com.example.realestateregister.controller;

import com.example.realestateregister.dto.BuildingRoleDto;
import com.example.realestateregister.dto.BuildingRoomDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {
    private final BuildingService buildingService;
    private final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public List<Building> listBuildings() {
        return buildingService.listBuildings();
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable("id") long id) {
        return buildingService.getBuildingById(id);
    }

    @PostMapping
    public long addBuildingAndReturnId(@RequestBody @Valid Building building) {
        return buildingService.addBuildingAndReturnId(building);
    }

    @PutMapping("/{id}")
    public void updateBuildingById(@RequestBody @Valid Building building, @PathVariable("id") long id) {
        buildingService.updateBuildingById(building, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBuildingById(@PathVariable("id") long id) {
        buildingService.deleteBuildingById(id);
    }

    @PostMapping("/room")
    public void addRoomToBuilding(@RequestBody @Valid BuildingRoomDto buildingRoomDto) {
        buildingService.addRoomToBuilding(buildingRoomDto);
    }

    @GetMapping("/{id}/room")
    public List<Room> listRoomsByBuildingId(@PathVariable("id") long id) {
        return buildingService.listRoomsByBuildingId(id);
    }

    @PostMapping("/role")
    public void addRoleToBuilding(@RequestBody @Valid BuildingRoleDto buildingRoleDto) {
        buildingService.addRoleToBuilding(buildingRoleDto);
    }

    @GetMapping("/{id}/role")
    public List<Role> listRolesByBuildingId(@PathVariable("id") long id) {
        return buildingService.listRolesByBuildingId(id);
    }
}

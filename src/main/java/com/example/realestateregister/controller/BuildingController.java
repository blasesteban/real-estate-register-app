package com.example.realestateregister.controller;

import com.example.realestateregister.dto.*;
import com.example.realestateregister.model.*;
import com.example.realestateregister.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @Operation(summary = "List all buildings", description = "Lists all the buildings")
    @GetMapping
    public List<Building> listBuildings() {
        return buildingService.listBuildings();
    }

    @Operation(summary = "Get building", description = "Gets a building by id")
    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable("id") long id) {
        return buildingService.getBuildingById(id);
    }

    @Operation(summary = "Add building", description = "Adds a new building")
    @PostMapping
    public ResponseEntity<?> addBuildingAndReturnId(@RequestBody @Valid BuildingDto building, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid building");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid building");
        }
        return ResponseEntity.ok(buildingService.addBuildingAndReturnId(building));
    }

    @Operation(summary = "Update building", description = "Updates a building by id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBuildingById(@RequestBody @Valid BuildingDto building, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid building");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid building");
        }
        buildingService.updateBuildingById(building, id);
        return ResponseEntity.ok("building is updated");
    }

    @Operation(summary = "Delete building", description = "Deletes a building by id")
    @DeleteMapping("/{id}")
    public void deleteBuildingById(@PathVariable("id") long id) {
        buildingService.deleteBuildingById(id);
    }

    @Operation(summary = "Add room to building", description = "Adds a room by id to a building by id")
    @PostMapping("/room")
    public ResponseEntity<String> addRoomToBuilding(@RequestBody @Valid BuildingRoomDto buildingRoomDto, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid room id or building id");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid room id or building id");
        }
        buildingService.addRoomToBuilding(buildingRoomDto);
        return ResponseEntity.ok("rooms are added to the building");
    }

    @Operation(summary = "Get rooms by building", description = "Gets all the rooms by a building by id")
    @GetMapping("/{id}/room")
    public List<Room> listRoomsByBuildingId(@PathVariable("id") long id) {
        return buildingService.listRoomsByBuildingId(id);
    }

    @Operation(summary = "Add role to building", description = "Adds a role by id to a building by id")
    @PostMapping("/role")
    public ResponseEntity<String> addRoleToBuilding(@RequestBody @Valid BuildingRoleDto buildingRoleDto, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid role id or building id");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid role id or building id");
        }
        buildingService.addRoleToBuilding(buildingRoleDto);
        return ResponseEntity.ok("roles are added to the building");
    }

    @Operation(summary = "Get roles by building", description = "Gets all the roles by a building by id")
    @GetMapping("/{id}/role")
    public List<Role> listRolesByBuildingId(@PathVariable("id") long id) {
        return buildingService.listRolesByBuildingId(id);
    }

    @Operation(summary = "Get persons by building", description = "Gets all the persons related to a building by id")
    @GetMapping("/{id}/person")
    public List<PersonBuildingDto> listPersonsByBuilding(@PathVariable("id") long id) {
        return buildingService.listPersonsByBuilding(id);
    }
}

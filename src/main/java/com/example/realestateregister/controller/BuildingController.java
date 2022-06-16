package com.example.realestateregister.controller;

import com.example.realestateregister.dto.BuildingDto;
import com.example.realestateregister.dto.BuildingRoleDto;
import com.example.realestateregister.dto.BuildingRoomDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.BuildingService;
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

    @GetMapping
    public List<Building> listBuildings() {
        return buildingService.listBuildings();
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable("id") long id) {
        return buildingService.getBuildingById(id);
    }

    @PostMapping
    public ResponseEntity<?> addBuildingAndReturnId(@RequestBody @Valid BuildingDto building, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid building");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid building");
        }
        return ResponseEntity.ok(buildingService.addBuildingAndReturnId(building));
    }

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

    @DeleteMapping("/{id}")
    public void deleteBuildingById(@PathVariable("id") long id) {
        buildingService.deleteBuildingById(id);
    }

    @PostMapping("/room")
    public ResponseEntity<String> addRoomToBuilding(@RequestBody @Valid BuildingRoomDto buildingRoomDto, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid building");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid building");
        }
        buildingService.addRoomToBuilding(buildingRoomDto);
        return ResponseEntity.ok("rooms are added to the building");
    }

    @GetMapping("/{id}/room")
    public List<Room> listRoomsByBuildingId(@PathVariable("id") long id) {
        return buildingService.listRoomsByBuildingId(id);
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRoleToBuilding(@RequestBody @Valid BuildingRoleDto buildingRoleDto, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid building");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid building");
        }
        buildingService.addRoleToBuilding(buildingRoleDto);
        return ResponseEntity.ok("roles are added to the building");
    }

    @GetMapping("/{id}/role")
    public List<Role> listRolesByBuildingId(@PathVariable("id") long id) {
        return buildingService.listRolesByBuildingId(id);
    }
}

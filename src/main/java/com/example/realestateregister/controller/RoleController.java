package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.exceptions.InvalidRoleTypeException;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(RoomController.class);

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "List all roles", description = "Lists all the roles")
    @GetMapping
    public List<Role> listRoles() {
        return roleService.listRoles();
    }

    @Operation(summary = "Get role", description = "Gets a role by id")
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable("id") long id) {
        return roleService.getRoleById(id);
    }

    @Operation(summary = "Add role", description = "Adds a new role")
    @PostMapping
    public ResponseEntity<?> addRoleAndReturnId(@RequestBody @Valid RoleDto role, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid role");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid role");
        }
        try {
            return ResponseEntity.ok(roleService.addRoleAndReturnId(role));
        } catch (InvalidRoleTypeException e) {
            return ResponseEntity.badRequest().body("invalid roleType");
        }
    }

    @Operation(summary = "Update role", description = "Updates a role by id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoleById(@RequestBody @Valid RoleDto role, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid role");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid role");
        }
        try {
            roleService.updateRoleById(role, id);
        } catch (InvalidRoleTypeException e) {
            return ResponseEntity.badRequest().body("invalid roleType");

        }
        return ResponseEntity.ok("role is updated");

    }

    @Operation(summary = "Delete role", description = "Deletes a role by id")
    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable("id") long id) {
        roleService.deleteRoleById(id);
    }
}

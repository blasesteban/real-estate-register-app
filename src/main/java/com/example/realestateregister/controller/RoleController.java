package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.RoleService;
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

    @GetMapping
    public List<Role> listRoles() {
        return roleService.listRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable("id") long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<?> addRoleAndReturnId(@RequestBody @Valid RoleDto role, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid role");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid role");
        }
        return ResponseEntity.ok(roleService.addRoleAndReturnId(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoleById(@RequestBody @Valid RoleDto role, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid role");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid role");
        }
        roleService.updateRoleById(role, id);
        return ResponseEntity.ok("role is updated");
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable("id") long id) {
        roleService.deleteRoleById(id);
    }
}

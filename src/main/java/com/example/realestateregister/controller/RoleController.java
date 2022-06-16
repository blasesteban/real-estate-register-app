package com.example.realestateregister.controller;

import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

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
    public long addRoleAndReturnId(@RequestBody @Valid Role role) {
        return roleService.addRoleAndReturnId(role);
    }

    @PutMapping("/{id}")
    public void updateRoleById(@RequestBody @Valid Role role, @PathVariable("id") long id) {
        roleService.updateRoleById(role, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable("id") long id) {
        roleService.deleteRoleById(id);
    }
}

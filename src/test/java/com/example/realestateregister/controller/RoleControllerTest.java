package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.exceptions.InvalidRoleTypeException;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.RoleType;
import com.example.realestateregister.service.RoleService;
import lombok.SneakyThrows;
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
class RoleControllerTest {
    @InjectMocks
    private RoleController roleController;
    @Mock
    private BindingResult br;
    @Mock
    private RoleService roleService;

    private final Role role1 = new Role(1, RoleType.ARCHITECT, new Person(), new Building());
    private final Role role2 = new Role(2, RoleType.OWNER, new Person(), new Building());
    private final Role role3 = new Role(3, RoleType.REALTOR, new Person(), new Building());
    private final List<Role> roleList = List.of(role1, role2, role3);

    private RoleDto roleDtoFromEntity(Role role) {
        return new RoleDto(role.getRoleType().toString());
    }

    @Test
    void listRoles() {
        when(roleService.listRoles()).thenReturn(roleList);
        List<Role> expected = roleController.listRoles();
        assertEquals(expected, roleList);
        assertEquals(expected.get(0), role1);
        assertEquals(expected.get(1), role2);
        assertEquals(expected.get(2), role3);
    }

    @Test
    void getRoleById() {
        when(roleService.getRoleById(1)).thenReturn(role1);
        Role expected = roleController.getRoleById(1);
        assertEquals(expected, role1);
    }

    @SneakyThrows
    @Test
    void addRoleAndReturnId() {
        when(br.hasErrors()).thenReturn(false);
        when(roleService.addRoleAndReturnId(new RoleDto())).thenReturn(role1.getId());
        ResponseEntity<?> expected = roleController.addRoleAndReturnId(new RoleDto(), br);
        assertEquals(expected.getBody(), role1.getId());
    }

    @Test
    void addRoleAndReturnIdWrong() {
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = roleController.addRoleAndReturnId(new RoleDto(), br);
        assertEquals(expected.getBody(), "invalid role");
    }

    @SneakyThrows
    @Test
    void updateRoleById() {
        when(br.hasErrors()).thenReturn(false);
        ResponseEntity<?> expected = roleController.updateRoleById(roleDtoFromEntity(role1), role1.getId(), br);
        verify(roleService).updateRoleById(roleDtoFromEntity(role1), role1.getId());
        assertEquals(expected.getBody(), "role is updated");
    }

    @Test
    void updateRoleByIdWrong() {
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = roleController.updateRoleById(new RoleDto(), role1.getId(), br);
        assertEquals(expected.getBody(), "invalid role");
    }

    @Test
    void deleteRoleById() {
        roleController.deleteRoleById(role1.getId());
        verify(roleService).deleteRoleById(role1.getId());
    }
}
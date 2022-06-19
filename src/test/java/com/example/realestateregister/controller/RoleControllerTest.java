package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.RoleType;
import com.example.realestateregister.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        return new RoleDto(role.getRoleType());
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

    @Test
    void addRoleAndReturnId() {
        when(br.hasErrors()).thenReturn(false);
        when(roleService.addRoleAndReturnId(roleDtoFromEntity(role1))).thenReturn(role1.getId());
        ResponseEntity<?> expected = roleController.addRoleAndReturnId(roleDtoFromEntity(role1), br);
        assertEquals(expected.getBody(), role1.getId());
    }

/*    @Test
    void updateRoleById() {
    }

    @Test
    void deleteRoleById() {
    }*/
}
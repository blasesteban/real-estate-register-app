package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.RoleType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;
    @Mock
    private RoleJpaDao roleJpaDao;

    private final Role role1 = new Role(1, RoleType.ARCHITECT, new Person(), new Building());
    private final Role role2 = new Role(2, RoleType.OWNER, new Person(), new Building());
    private final Role role3 = new Role(3, RoleType.REALTOR, new Person(), new Building());
    private final List<Role> roleList = List.of(role1, role2, role3);

    private RoleDto roleDtoFromEntity(Role role) {
        return new RoleDto(role.getRoleType().toString());
    }

    @Test
    void listRoles() {
        when(roleJpaDao.findAll()).thenReturn(roleList);
        List<Role> expected = roleService.listRoles();
        assertEquals(expected, roleList);
        assertEquals(expected.get(0), role1);
        assertEquals(expected.get(1), role2);
        assertEquals(expected.get(2), role3);
    }

    @Test
    void getRoleById() {
        when(roleJpaDao.findById(1L)).thenReturn(Optional.of(role1));
        Role expected = roleService.getRoleById(1);
        assertEquals(expected, role1);
        assertEquals(expected.getPerson(), role1.getPerson());
        assertEquals(expected.getBuilding(), role1.getBuilding());
    }

/*    @SneakyThrows
    @Test
    void addRoleAndReturnId() {
    }*/

    @SneakyThrows
    @Test
    void updateRoleById() {
        when(roleJpaDao.findById(role1.getId())).thenReturn(Optional.of(role1));
        roleService.updateRoleById(roleDtoFromEntity(role1), role1.getId());
        verify(roleJpaDao).save(role1);
    }

    @Test
    void deleteRoleById() {
        roleService.deleteRoleById(role1.getId());
        verify(roleJpaDao).deleteById(role1.getId());
    }
}
package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.exceptions.InvalidRoleTypeException;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleJpaDao roleDao;

    @Autowired
    public RoleService(RoleJpaDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> listRoles() {
        return roleDao.findAll();
    }

    public Role getRoleById(long id) {
        return roleDao.findById(id).orElseThrow();
    }

    public long addRoleAndReturnId(RoleDto roleDto) throws InvalidRoleTypeException {
        Role role = new Role();
        try {
            role.setRoleType(RoleType.valueOf(roleDto.getRoleType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleTypeException();
        }
        return roleDao.save(role).getId();
    }

    public void updateRoleById(RoleDto roleDto, long id) throws InvalidRoleTypeException {
        Role role = roleDao.findById(id).orElseThrow();
        try {
            role.setRoleType(RoleType.valueOf(roleDto.getRoleType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleTypeException();
        }
        roleDao.save(role);
    }

    public void deleteRoleById(long id) {
        roleDao.deleteById(id);
    }
}

package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dto.RoleDto;
import com.example.realestateregister.model.Role;
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

    public long addRoleAndReturnId(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleType(roleDto.getRoleType());
        return roleDao.save(role).getId();
    }

    public void updateRoleById(RoleDto roleDto, long id) {
        Role role = roleDao.findById(id).orElseThrow();
        role.setRoleType(roleDto.getRoleType());
        roleDao.save(role);
    }

    public void deleteRoleById(long id) {
        roleDao.deleteById(id);
    }
}

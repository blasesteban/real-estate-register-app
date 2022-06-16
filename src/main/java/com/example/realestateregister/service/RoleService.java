package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoleJpaDao;
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

    public long addRoleAndReturnId(Role role) {
        return roleDao.save(role).getId();
    }

    public void updateRoleById(Role newRole, long id) {
        Role role = roleDao.findById(id).orElseThrow();
        role.setRoleType(newRole.getRoleType());
        role.setBuilding(newRole.getBuilding());
        role.setPerson(newRole.getPerson());
        roleDao.save(role);
    }

    public void deleteRoleById(long id) {
        roleDao.deleteById(id);
    }
}

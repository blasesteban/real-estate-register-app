package com.example.realestateregister.service;

import com.example.realestateregister.dao.BuildingJpaDao;
import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dao.RoomJpaDao;
import com.example.realestateregister.dto.BuildingRoleDto;
import com.example.realestateregister.dto.BuildingRoomDto;
import com.example.realestateregister.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    private final BuildingJpaDao buildingDao;
    private final RoomJpaDao roomDao;
    private final RoleJpaDao roleDao;

    @Autowired
    public BuildingService(BuildingJpaDao buildingDao, RoomJpaDao roomDao, RoleJpaDao roleDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.roleDao = roleDao;
    }

    public List<Building> listBuildings() {
        return buildingDao.findAll();
    }

    public Building getBuildingById(long id) {
        return buildingDao.findById(id).orElseThrow();
    }

    public long addBuildingAndReturnId(Building building) {
        return buildingDao.save(building).getId();
    }

    public void updateBuildingById(Building newBuilding, long id) {
        Building building = buildingDao.findById(id).orElseThrow();
        building.setSquareMeters(newBuilding.getSquareMeters());
        building.setPrice(newBuilding.getPrice());
        building.setRoles(newBuilding.getRoles());
        buildingDao.save(building);
    }

    public void deleteBuildingById(long id) {
        buildingDao.deleteById(id);
    }


    public void addRoomToBuilding(BuildingRoomDto buildingRoomDto) {
        roomDao.addToBuilding(buildingRoomDto.getRoomId(), buildingDao.findById(buildingRoomDto.getBuildingId()).orElseThrow());
    }

    public List<Room> listRoomsByBuildingId(long id) {
        return roomDao.listRoomsByBuildingId(id);
    }

    public void addRoleToBuilding(BuildingRoleDto buildingRoleDto) {
        roleDao.addToBuilding(buildingRoleDto.getRoleId(), buildingDao.findById(buildingRoleDto.getBuildingId()).orElseThrow());
    }

    public List<Role> listRolesByBuildingId(long id) {
        return roleDao.listRolesByBuildingId(id);
    }
 /*    public Map<RoleType, Person> listPersonsByBuildingId(long id) {
        return roleDao.listRolesByBuildingId(id)
                .stream()
                .collect(Collectors.toMap(Role::getRoleType, role -> personDao.findById(role.getPersonId())));
    }

    public Map<RoleType, Building> listBuildingsByPersonId(long id) {
        return roleDao.listRolesByPersonId(id)
                .stream()
                .collect(Collectors.toMap(Role::getRoleType, role -> buildingDao.findById(role.getBuildingId())));
    }*/

}

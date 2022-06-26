package com.example.realestateregister.service;

import com.example.realestateregister.dao.BuildingJpaDao;
import com.example.realestateregister.dao.PersonJpaDao;
import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dto.BuildingPersonDto;
import com.example.realestateregister.dto.PersonDto;
import com.example.realestateregister.dto.PersonRoleDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.model.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonJpaDao personDao;
    private final BuildingJpaDao buildingDao;
    private final RoleJpaDao roleDao;

    @Autowired
    public PersonService(PersonJpaDao personDao, BuildingJpaDao buildingDao, RoleJpaDao roleDao) {
        this.personDao = personDao;
        this.buildingDao = buildingDao;
        this.roleDao = roleDao;
    }

    public List<Person> listPersons() {
        return personDao.findAll();
    }

    public Person getPersonById(long id) {
        return personDao.findById(id).orElseThrow();
    }

    public long addPersonAndReturnId(PersonDto personDto) {
        Person person = new Person();
        person.setFirstname(personDto.getFirstname());
        person.setSurname(personDto.getSurname());
        person.setAddress(personDto.getAddress());
        person.setPhoneNumber(personDto.getPhoneNumber());
        return personDao.save(person).getId();
    }

    public void updatePersonById(PersonDto personDto, long id) {
        Person person = personDao.findById(id).orElseThrow();
        person.setFirstname(personDto.getFirstname());
        person.setSurname(personDto.getSurname());
        person.setAddress(personDto.getAddress());
        person.setPhoneNumber(personDto.getPhoneNumber());
        personDao.save(person);
    }

    public void deletePersonById(long id) {
        personDao.deleteById(id);
    }

    public void addRoleToPerson(PersonRoleDto personRoleDto) {
        roleDao.addToPerson(personRoleDto.getRoleId(), personDao.findById(personRoleDto.getPersonId()).orElseThrow());
    }

    public List<Role> listRolesByPerson(long id) {
        return roleDao.listRolesByPerson(id);
    }


    /*    public List<Person> listAllArchitect() {
            return roleDao.listRolesByRoleType(RoleType.valueOf("ARCHITECT"))
                    .stream()
                    .map(role -> personDao.findById(role.getPersonId()))
                    .collect(Collectors.toList());
        }

        public List<Person> listAllRealtor() {
            return roleDao.listRolesByRoleType(RoleType.valueOf("REALTOR"))
                    .stream()
                    .map(role -> personDao.getPersonById(role.getPersonId()))
                    .collect(Collectors.toList());
        }

        public List<Person> listAllOwner() {
            return roleDao.listRolesByRoleType(RoleType.valueOf("OWNER"))
                    .stream()
                    .map(role -> personDao.getPersonById(role.getPersonId()))
                    .collect(Collectors.toList());
        }

        public List<Building> listAllBuildingsByArchitectId(long id) {
            return roleDao.listRolesByPersonId(id)
                    .stream()
                    .filter(role -> role.getRoleType().equals(RoleType.valueOf("ARCHITECT")))
                    .map(role -> buildingDao.getBuildingById(role.getBuildingId()))
                    .collect(Collectors.toList());
        }

        public List<Building> listAllBuildingsByRealtorId(long id) {
            return roleDao.listRolesByPersonId(id)
                    .stream()
                    .filter(role -> role.getRoleType().equals(RoleType.valueOf("REALTOR")))
                    .map(role -> buildingDao.getBuildingById(role.getBuildingId()))
                    .collect(Collectors.toList());
        }

        public List<Building> listAllBuildingsByOwnerId(long id) {
            return roleDao.listRolesByPersonId(id)
                    .stream()
                    .filter(role -> role.getRoleType().equals(RoleType.valueOf("OWNER")))
                    .map(role -> buildingDao.getBuildingById(role.getBuildingId()))
                    .collect(Collectors.toList());
        }*/
    public List<BuildingPersonDto> listBuildingsByPerson(long id) {
        List<BuildingPersonDto> buildingPersonDtoList = new ArrayList<>();
        for (Object[] objects : buildingDao.listBuildingsByPerson(id)) {
            RoleType roleType = (RoleType) objects[0];
            Building building = (Building) objects[1];
            buildingPersonDtoList.add(new BuildingPersonDto(
                    building.getSquareMeters(),
                    building.getPrice(),
                    building.getRooms(),
                    roleType
            ));
        }
        return buildingPersonDtoList;
    }
}

package com.example.realestateregister.service;

import com.example.realestateregister.dao.PersonJpaDao;
import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dto.PersonRoleDto;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonJpaDao personDao;
    private final RoleJpaDao roleDao;

    @Autowired
    public PersonService(PersonJpaDao personDao, RoleJpaDao roleDao) {
        this.personDao = personDao;
        this.roleDao = roleDao;
    }

    public List<Person> listPersons() {
        return personDao.findAll();
    }

    public Person getPersonById(long id) {
        return personDao.findById(id).orElseThrow();
    }

    public long addPersonAndReturnId(Person person) {
        return personDao.save(person).getId();
    }

    public void updatePersonById(Person newPerson, long id) {
        Person person = personDao.findById(id).orElseThrow();
        person.setFirstname(newPerson.getFirstname());
        person.setSurname(newPerson.getSurname());
        person.setAddress(newPerson.getAddress());
        person.setPhoneNumber(newPerson.getPhoneNumber());
        person.setRoles(newPerson.getRoles());
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


}

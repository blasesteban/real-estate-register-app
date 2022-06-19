package com.example.realestateregister.service;

import com.example.realestateregister.dao.PersonJpaDao;
import com.example.realestateregister.dao.RoleJpaDao;
import com.example.realestateregister.dto.PersonDto;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonJpaDao personJpaDao;
    @Mock
    private RoleJpaDao roleJpaDao;
    private final Role role1 = new Role();
    private final Role role2 = new Role();
    private final Role role3 = new Role();

    private final Person person1 = new Person(1, "a", "a", "a", "003610111111111", List.of(role1, role2));
    private final Person person2 = new Person(2, "b", "b", "b", "003620222222222", List.of(role2, role3));
    private final Person person3 = new Person(3, "c", "c", "c", "003630333333333", List.of(role1, role3));
    private final List<Person> personList = List.of(person1, person2, person3);

    private PersonDto personDtoFromEntity(Person person) {
        return new PersonDto(person.getFirstname(), person.getSurname(), person.getAddress(), person.getPhoneNumber());
    }

    @Test
    void listPersons() {
        when(personJpaDao.findAll()).thenReturn(personList);
        List<Person> expected = personService.listPersons();
        assertEquals(expected, personList);
        assertEquals(expected.get(0), person1);
        assertEquals(expected.get(1), person2);
        assertEquals(expected.get(2), person3);
    }

    @Test
    void getPersonById() {
        when(personJpaDao.findById(1L)).thenReturn(Optional.of(person1));
        Person expected = personService.getPersonById(1);
        assertEquals(expected, person1);
        assertEquals(expected.getRoles(), person1.getRoles());
    }

/*    @Test
    void addPersonAndReturnId() {
    }

    @Test
    void updatePersonById() {
    }

    @Test
    void deletePersonById() {
    }

    @Test
    void addRoleToPerson() {
    }*/

    @Test
    void listRolesByPerson() {
        when(roleJpaDao.listRolesByPerson(person1.getId())).thenReturn(person1.getRoles());
        List<Role> expected1 = personService.listRolesByPerson(person1.getId());
        assertEquals(expected1, person1.getRoles());
        when(roleJpaDao.listRolesByPerson(person2.getId())).thenReturn(person2.getRoles());
        List<Role> expected2 = personService.listRolesByPerson(person2.getId());
        assertEquals(expected2, person2.getRoles());
        when(roleJpaDao.listRolesByPerson(person3.getId())).thenReturn(person3.getRoles());
        List<Role> expected3 = personService.listRolesByPerson(person3.getId());
        assertEquals(expected3, person3.getRoles());
    }
}
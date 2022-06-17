package com.example.realestateregister.controller;

import com.example.realestateregister.dto.PersonDto;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
    @InjectMocks
    private PersonController personController;
    @Mock
    private BindingResult br;
    @Mock
    private PersonService personService;

    private final Role role1 = new Role();
    private final Role role2 = new Role();
    private final Role role3 = new Role();

    private final Person person1 = new Person(1, "a", "a", "a", "003610111111111", List.of(role1, role2));
    private final Person person2 = new Person(2, "b", "b", "b", "003620222222222", List.of(role2, role3));
    private final Person person3 = new Person(3, "c", "c", "c", "003630333333333", List.of(role1, role3));
    private final List<Person> personList = List.of(person1, person2, person3);

    @Test
    void listPersons() {
        when(personService.listPersons()).thenReturn(personList);
        List<Person> expected = personController.listPersons();
        assertEquals(expected, personList);
        assertEquals(expected.get(0), person1);
        assertEquals(expected.get(1), person2);
        assertEquals(expected.get(2), person3);
    }

    @Test
    void getPersonById() {
        when(personService.getPersonById(1)).thenReturn(person1);
        Person expected = personController.getPersonById(1);
        assertEquals(expected, person1);
        assertEquals(expected.getRoles(), person1.getRoles());
    }

    @Test
    void addPersonAndReturnId() {
        when(br.hasErrors()).thenReturn(false);
        when(personService.addPersonAndReturnId(new PersonDto(person1.getFirstname(), person1.getSurname(), person1.getAddress(), person1.getPhoneNumber()))).thenReturn(person1.getId());
        ResponseEntity<?> expected = personController.addPersonAndReturnId(new PersonDto(person1.getFirstname(), person1.getSurname(), person1.getAddress(), person1.getPhoneNumber()), br);
        assertEquals(expected.getBody(), person1.getId());
    }

    @Test
    void updatePersonById() {
    }

    @Test
    void deletePersonById() {
    }

    @Test
    void addRoleToPerson() {
    }

    @Test
    void listRolesByPerson() {
    }
}
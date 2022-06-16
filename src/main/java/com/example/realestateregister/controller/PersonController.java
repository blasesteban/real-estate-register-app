package com.example.realestateregister.controller;

import com.example.realestateregister.dto.PersonRoleDto;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<Person> listPersons() {
        return personService.listPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public long addPersonAndReturnId(@RequestBody @Valid Person person) {
        return personService.addPersonAndReturnId(person);
    }

    @PutMapping("/{id}")
    public void updatePersonById(@RequestBody @Valid Person person, @PathVariable("id") long id) {
        personService.updatePersonById(person, id);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable("id") long id) {
        personService.deletePersonById(id);
    }

    @PostMapping("/role")
    public void addRoleToPerson(@RequestBody @Valid PersonRoleDto personRoleDto) {
        personService.addRoleToPerson(personRoleDto);
    }

    @GetMapping("/{id}/role")
    public List<Role> listRolesByPerson(@PathVariable("id") long id) {
        return personService.listRolesByPerson(id);
    }
}

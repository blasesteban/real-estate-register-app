package com.example.realestateregister.controller;

import com.example.realestateregister.dto.PersonDto;
import com.example.realestateregister.dto.PersonRoleDto;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

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
    public ResponseEntity<?> addPersonAndReturnId(@RequestBody @Valid PersonDto person, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid person");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid person");
        }
        return ResponseEntity.ok(personService.addPersonAndReturnId(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersonById(@RequestBody @Valid PersonDto person, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid person");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid person");
        }
        personService.updatePersonById(person, id);
        return ResponseEntity.ok("person is updated");
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable("id") long id) {
        personService.deletePersonById(id);
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRoleToPerson(@RequestBody @Valid PersonRoleDto personRoleDto, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid building");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid building");
        }
        personService.addRoleToPerson(personRoleDto);
        return ResponseEntity.ok("role is added to person");
    }

    @GetMapping("/{id}/role")
    public List<Role> listRolesByPerson(@PathVariable("id") long id) {
        return personService.listRolesByPerson(id);
    }
}

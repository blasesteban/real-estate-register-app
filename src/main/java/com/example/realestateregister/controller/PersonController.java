package com.example.realestateregister.controller;

import com.example.realestateregister.dto.BuildingPersonDto;
import com.example.realestateregister.dto.PersonDto;
import com.example.realestateregister.dto.PersonRoleDto;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import com.example.realestateregister.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "List all persons", description = "Lists all the persons")
    @GetMapping()
    public List<Person> listPersons() {
        return personService.listPersons();
    }

    @Operation(summary = "Get person", description = "Gets a person by id")
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") long id) {
        return personService.getPersonById(id);
    }

    @Operation(summary = "Add person", description = "Adds a new person")
    @PostMapping
    public ResponseEntity<?> addPersonAndReturnId(@RequestBody @Valid PersonDto person, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid person");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid person");
        }
        return ResponseEntity.ok(personService.addPersonAndReturnId(person));
    }

    @Operation(summary = "Update person", description = "Updates a person by id")
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

    @Operation(summary = "Delete person", description = "Deletes a person by id")
    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable("id") long id) {
        personService.deletePersonById(id);
    }

    @Operation(summary = "Add role to person", description = "Adds a role by id to a person by id")
    @PostMapping("/role")
    public ResponseEntity<String> addRoleToPerson(@RequestBody @Valid PersonRoleDto personRoleDto, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid role id or person id");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid role id or person id");
        }
        personService.addRoleToPerson(personRoleDto);
        return ResponseEntity.ok("role is added to person");
    }

    @Operation(summary = "Get roles by person", description = "Gets all the roles by a person by id")
    @GetMapping("/{id}/role")
    public List<Role> listRolesByPerson(@PathVariable("id") long id) {
        return personService.listRolesByPerson(id);
    }

    @Operation(summary = "Get buildings by person", description = "Gets all the buildings related to a person by id")
    @GetMapping("/{id}/building")
    public List<BuildingPersonDto> listBuildingsByPerson(@PathVariable("id") long id) {
        return personService.listBuildingsByPerson(id);
    }
}

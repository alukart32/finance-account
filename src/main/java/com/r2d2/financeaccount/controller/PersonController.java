package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/person/")
public class PersonController {
    PersonService personService;
    PersonRepository personRepository;

    public PersonController(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @RequestMapping("/showAll")
    public @ResponseBody ResponseEntity<Set<PersonDTO>> showPeople(){
        Set<PersonDTO> people = personService.getAll();
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @RequestMapping("{personId}/show")
    public @ResponseBody ResponseEntity<PersonDTO> showPerson(@PathVariable String personId){
        PersonDTO person = personService.getById(Long.valueOf(personId));
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @RequestMapping(value = "createPerson", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public @ResponseBody ResponseEntity<PersonDTO> addPerson(@Valid @RequestBody PersonNewDTO personDTO){
        PersonDTO person = personService.create(personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }
/*

    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public @ResponseBody ResponseEntity<AccountDTO> addAccount(@Valid @RequestBody AccountNewDTO accountNewDTO){
        PersonDTO person =
        return new ResponseEntity(person, HttpStatus.OK);
    }
*/

    @RequestMapping(value = "{personId}/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonNewDTO personDTO,
                                                              @PathVariable String personId) {
        PersonDTO person = personService.update(Long.valueOf(personId), personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }
}
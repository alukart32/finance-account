package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.*;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/person/")
public class PersonController {

    PersonRepository personRepository;

    PersonService personService;
    AccountService accountService;
    CurrencyService currencyService;

    public PersonController(PersonRepository personRepository, PersonService personService,
                            AccountService accountService, CurrencyService currencyService) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.accountService = accountService;
        this.currencyService = currencyService;
    }


    @RequestMapping("/showAll")
    public ResponseEntity<Set<PersonDTO>> showPeople(){
        Set<PersonDTO> people = personService.getAll();
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @RequestMapping("/{personId}/show")
    public ResponseEntity<PersonDTO> showPerson(@PathVariable String personId){
        PersonDTO person = personService.getById(Long.valueOf(personId));
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @RequestMapping(value = "createPerson", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonNewDTO personDTO){
        PersonDTO person = personService.create(personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}/update", method = RequestMethod.PUT)
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonNewDTO personDTO,
                                                              @PathVariable String personId) {
        PersonDTO person = personService.update(Long.valueOf(personId), personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }
}
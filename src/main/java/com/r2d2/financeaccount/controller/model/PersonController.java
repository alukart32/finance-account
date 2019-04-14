package com.r2d2.financeaccount.controller.model;

import com.r2d2.financeaccount.data.dto.modelDTO.PersonDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.PersonNewDTO;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("{id}")
    public ResponseEntity<PersonDTO> showPerson(@PathVariable("id") Long personId){
        PersonDTO person = personService.getById(personId);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonNewDTO personDTO){
        PersonDTO person = personService.create(personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonNewDTO personDTO,
                                                              @PathVariable("id") Long personId) {
        PersonDTO person = personService.update(personId, personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long personId){
        personService.delete(personId);
        return null;
    }
}
package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.services.facedes.PersonFacade;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/person/")
public class PersonController {
    PersonService personService;
    PersonRepository personRepository;

    PersonFacade personFacade;

    public PersonController(PersonService personService, PersonRepository personRepository, PersonFacade personFacade) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.personFacade = personFacade;
    }

    @RequestMapping("{personId}/show")
    public @ResponseBody ResponseEntity<PersonDTO> showPerson(@PathVariable String personId){
        PersonDTO person = personFacade.getById(personId);
        return new ResponseEntity(person, HttpStatus.OK);
    }


    @RequestMapping(value = "createPerson", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public @ResponseBody ResponseEntity<PersonDTO> addPerson(@Valid @RequestBody PersonNewDTO personDTO){
        PersonDTO person = personFacade.create(personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/person/{personId}/update", method = RequestMethod.GET)
    public String updateById(@PathVariable String personId, Model model) {
        model.addAttribute("person", personRepository.findById(Long.valueOf(personId)));
        return "/person/update-person";
    }

    @PostMapping
    @RequestMapping("person")
    public String saveOrUpdate(@ModelAttribute PersonCommand personCommand){
        PersonCommand savedCommand = personService.saveOrUpdate(personCommand);
        return "redirect:/person/"+savedCommand.getId()+"/show";
    }
    */
}
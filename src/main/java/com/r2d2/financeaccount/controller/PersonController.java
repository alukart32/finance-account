package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.services.facedes.PersonFacade;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /*
    @RequestMapping("/person/add-person")
    public String addPerson(Model model){
        model.addAttribute("person", new PersonCommand());
        return "/person/add-person";
    }


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
package com.r2d2.financeaccount.services.facedes;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.services.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class PersonFacade {
    PersonService personService;

    ModelMapper modelMapper;

    public PersonFacade(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    public PersonDTO getById(String id){
        return convertPersonToPersonDTO(personService.getById(Long.valueOf(id)));
    }

    public PersonDTO create(PersonNewDTO personDTO){
        return convertPersonToPersonDTO(personService.create(convertPersonNewDTOToPerson(personDTO)));
    }

    private PersonDTO convertPersonToPersonDTO(Person person){
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }

    private Person convertPersonDTOToPerson(PersonDTO personDto){
        Person person = modelMapper.map(personDto, Person.class);

        if (personDto.getId() != null) {
            Person oldPerson = personService.getById(personDto.getId());
            person.setId(oldPerson.getId());
        }
        return person;
    }

    private Person convertPersonNewDTOToPerson(PersonNewDTO personDto){
        Person person = modelMapper.map(personDto, Person.class);
        return person;
    }
}

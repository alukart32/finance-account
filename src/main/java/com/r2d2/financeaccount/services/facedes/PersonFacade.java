package com.r2d2.financeaccount.services.facedes;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.services.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

    private PersonDTO convertPersonToPersonDTO(Person person){
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }
}

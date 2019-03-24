package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class PersonServiceImpl  implements PersonService {
    PersonRepository personRepository;
    ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDTO getById(Long personId) {
        Person person = personRepository.findById(personId).
                orElseThrow(NotFoundException::new);
        return modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public PersonDTO create(PersonNewDTO newPerson) {
        final Person person = modelMapper.map(newPerson, Person.class);;

        person.setFirstName(newPerson.getFirstName());
        person.setSecondName(newPerson.getSecondName());
        person.setUserName(newPerson.getUserName());
        person.setRegisterDate(OffsetDateTime.now());

        Person savedPerson = personRepository.save(person);
        return modelMapper.map(savedPerson, PersonDTO.class);
    }

    @Override
    public Set<PersonDTO> getAll(){
        Set<PersonDTO> peopleDTO = new HashSet<>();

        for (Person person: personRepository.findAll()) {
            peopleDTO.add(modelMapper.map(person, PersonDTO.class));
        }
        return peopleDTO;
    }

    @Override
    public PersonDTO update(Long personId, PersonNewDTO personDTO){
        Person person = modelMapper.map(getById(Long.valueOf(personId)), Person.class);

        if(person != null){
            Person updatedPerson = modelMapper.map(personDTO, Person.class);

            if(!person.getFirstName().equals(updatedPerson.getFirstName()))
                person.setFirstName((updatedPerson.getFirstName()));

            if(!person.getSecondName().equals(updatedPerson.getSecondName()))
                person.setSecondName((updatedPerson.getSecondName()));

            if(!person.getUserName().equals(updatedPerson.getUserName()))
                person.setUserName(updatedPerson.getUserName());
        }

        saveOrUpdate(person);
        return modelMapper.map(person, PersonDTO.class);
    }


    @Override
    public Person saveOrUpdate(Person p) {
        Person person = personRepository.save(p);
        return person;
    }

    @Override
    public void delete(Long personId) {}
}

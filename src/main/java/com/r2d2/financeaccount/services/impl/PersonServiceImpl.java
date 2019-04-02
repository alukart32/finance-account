package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.modelDTO.PersonDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
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

    /**
     * Creating of a new person.
     *
     * @param newPerson
     *        Data that should be attach to a new person entity
     *
     * @return All object PersonDTO(was he updated or not it doesn't matter)
     */
    @Override
    public PersonDTO create(PersonNewDTO newPerson) {
        final Person person = modelMapper.map(newPerson, Person.class);
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

    /** Update person by his id
     * @param personId
     *        target person id
     *
     * @param personDTO
     *        data that will be attach to target person
     *
     * @return All object PersonDTO(was he updated or not it doesn't matter)
     */
    @Override
    public PersonDTO update(Long personId, PersonNewDTO personDTO){
        Person person = modelMapper.map(getById(Long.valueOf(personId)), Person.class);

        boolean updated = true;

        if(person != null){
            Person updatedPerson = modelMapper.map(personDTO, Person.class);

            if(!person.getFirstName().equals(updatedPerson.getFirstName())) {
                person.setFirstName((updatedPerson.getFirstName()));
                updated = false;
            }

            if(!person.getSecondName().equals(updatedPerson.getSecondName())) {
                person.setSecondName((updatedPerson.getSecondName()));
                updated = false;
            }

            if(!person.getUserName().equals(updatedPerson.getUserName())) {
                person.setUserName(updatedPerson.getUserName());
                updated = false;
            }
        }

        if(!updated)
            saveOrUpdate(person);
        return modelMapper.map(person, PersonDTO.class);
    }

    /**
     * Adding a person in DB.
     * If he was created than he will be added.
     * Otherwise, he will be updated.
     * @param p target
     * @return
     */
    @Override
    public Person saveOrUpdate(Person p) {
        return personRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

}

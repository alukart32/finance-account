package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.data.dto.modelDTO.PersonDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class PersonServiceImpl  implements PersonService {
    PersonRepository personRepository;

    OrikaMapper mapper;

    public PersonServiceImpl(PersonRepository personRepository, OrikaMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonDTO getById(Long personId) {
        Person person = personRepository.findById(personId).
                orElseThrow(NotFoundException::new);
        return mapper.map(person, PersonDTO.class);
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
    @Transactional
    public PersonDTO create(PersonNewDTO newPerson) {
        final Person person = mapper.map(newPerson, Person.class);
        person.setRegisterDate(OffsetDateTime.now());
        Person savedPerson = personRepository.save(person);
        return mapper.map(savedPerson, PersonDTO.class);
    }


    @Override
    public Set<PersonDTO> getAll(){
        Set<Person> people = new HashSet<>();
        personRepository.findAll().iterator().forEachRemaining(people::add);
        return  mapper.mapAsSet(people, PersonDTO.class);
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
    @Transactional
    public PersonDTO update(Long personId, PersonNewDTO personDTO){
        Person person = mapper.map(getById(Long.valueOf(personId)), Person.class);

        boolean updated = true;

        if(person != null){
            Person updatedPerson = mapper.map(personDTO, Person.class);

            if(!person.getFirstName().equals(updatedPerson.getFirstName())) {
                person.setFirstName((updatedPerson.getFirstName()));
                updated = false;
            }

            if(!person.getSecondName().equals(updatedPerson.getSecondName())) {
                person.setSecondName((updatedPerson.getSecondName()));
                updated = false;
            }

            if(!person.getUsername().equals(updatedPerson.getUsername())) {
                person.setUsername(updatedPerson.getUsername());
                updated = false;
            }
        }

        if(!updated)
            saveOrUpdate(person);
        return mapper.map(person, PersonDTO.class);
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

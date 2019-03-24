package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonServiceImpl  implements PersonService {
    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getById(Long personId) {
        Person person = personRepository.findById(personId).
                orElseThrow(() -> new RuntimeException("Person id:" + personId + " not found!"));
        return person;
    }

    @Override
    public Person create(Person newPerson) {
        final Person person = new Person();

        person.setFullName(newPerson.getFullName());
        person.setUserName(newPerson.getUserName());

        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

    @Override
    public Set<Person> getAll() {
        return null;
    }

    @Override
    public Person saveOrUpdate(Person p) {
        Person person = personRepository.save(p);
        return person;
    }

    @Override
    public void delete(Long personId) {

    }
}

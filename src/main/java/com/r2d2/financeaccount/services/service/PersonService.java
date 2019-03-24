package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;

import java.util.Set;

public interface PersonService {

    Person getById(Long personId);

    Set<Person> getAll();

    Person create(Person newPerson);

    Person saveOrUpdate(Person person);

    void delete(Long personId);
}

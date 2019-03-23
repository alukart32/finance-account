package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.model.Person;

import java.util.Set;

public interface PersonService {

    Person getById(Long personId);

    Set<Person> getAll();

    PersonDTO saveOrUpdate(PersonDTO command);

    PersonDTO update(Long personId);

    void delete(Long personId);
}

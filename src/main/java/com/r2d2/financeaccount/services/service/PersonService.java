package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.PersonDTO;
import com.r2d2.financeaccount.data.dto.PersonNewDTO;
import com.r2d2.financeaccount.data.model.Person;

import java.util.Set;

public interface PersonService {

    PersonDTO getById(Long personId);

    Set<PersonDTO> getAll();

    PersonDTO create(PersonNewDTO newPerson);

    PersonDTO update(Long personId, PersonNewDTO person);

    Person saveOrUpdate(Person person);

    void delete(Long personId);
}

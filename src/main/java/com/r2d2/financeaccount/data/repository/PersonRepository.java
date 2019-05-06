package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByUsername(String username);

    Optional<Person> getByUsername(String username);

    Optional<Person> findById(Long id);


}

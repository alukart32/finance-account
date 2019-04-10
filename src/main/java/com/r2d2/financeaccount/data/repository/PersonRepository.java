package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Person;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByUserName(String name);

    Optional<Person> findById(Long id);

}

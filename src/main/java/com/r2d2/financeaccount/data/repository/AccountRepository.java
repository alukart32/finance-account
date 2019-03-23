package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    Optional<Account> findById(Long aLong);

    Optional<Account> findByOwner(Person person);

    Optional<Account> findByDescription(String description);
}

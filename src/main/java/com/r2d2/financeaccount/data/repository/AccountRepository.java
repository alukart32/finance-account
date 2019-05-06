package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Person;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    Optional<Account> findById(Long accountId);

    @Query("from Account a where a.id = ?1")
    @Lock(PESSIMISTIC_WRITE)
    Optional<Account> findByIdForUpdate(Long id);

    Optional<Account> findByName(String name);

    Set<Account> findAllByOwner(Person p);

    @Query("from Account a where a.id = :id and a.owner = :owner")
    Account findByOwner(@Param("owner") Person p, @Param("id") Long accountId);
}

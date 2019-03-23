package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.txn.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}

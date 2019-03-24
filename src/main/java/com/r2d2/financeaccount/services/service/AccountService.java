package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.AccountDTO;
import com.r2d2.financeaccount.data.dto.TransactionDTO;
import com.r2d2.financeaccount.data.model.Account;

import java.util.Set;

public interface AccountService {
    Account getById(Long accountId);

    Set<Account> getAll(Long personId);

    Account create(Account newAccount);

    Account saveOrUpdate(AccountDTO accountDTO);

    Account update(Long personId, Long accountId);

    void delete(Long id);

    void addTransaction(Long accountId, TransactionDTO txn);
}

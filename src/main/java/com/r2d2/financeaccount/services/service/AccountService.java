package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.AccountDTO;
import com.r2d2.financeaccount.data.dto.TransactionDTO;
import com.r2d2.financeaccount.data.model.Account;

import java.util.Set;

public interface AccountService {
    AccountDTO getAccountById(Long accountId);

    Set<Account> getAccounts(Long personId);

    AccountDTO saveOrUpdate(AccountDTO accountDTO);

    AccountDTO update(Long personId, Long accountId);

    void delete(Long id);

    void addTransaction(Long accountId, TransactionDTO txn);
}

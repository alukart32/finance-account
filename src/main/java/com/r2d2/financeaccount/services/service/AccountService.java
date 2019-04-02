package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.modelDTO.AccountDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountNewDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.TransactionDTO;
import com.r2d2.financeaccount.data.model.Account;

import java.util.Set;

public interface AccountService {
    AccountDTO getById(Long accountId);

    CurrencyDTO getCurrency(Long accountId);

    Set<AccountDTO> getAll(Long personId);

    AccountDTO addAccount(Long personId, AccountNewDTO accountNewDTO);

    AccountDTO update(Long accountId, AccountNewDTO accountNewDTO);

    Account saveOrUpdate(Account account);

    void delete(Long id);

    void removeFrom(Long personId, Long accountId);

    void addTransaction(Long accountId, TransactionDTO txn);
}

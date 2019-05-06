package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.modelDTO.AccountDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountNewDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyDTO;
import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Person;

import java.util.Set;

public interface AccountService {
    AccountDTO getById(Long accountId);

    Account getByOwner(Person person, Long accountId);

    CurrencyDTO getCurrency(Long accountId);

    Set<AccountDTO> getAll();

    AccountDTO addAccount(Long personId, AccountNewDTO accountNewDTO);

    AccountDTO update(Long accountId, AccountNewDTO accountNewDTO);

    Account save(Account account);

    void delete(Long id);

    void removeFrom(Long personId, Long accountId);
}

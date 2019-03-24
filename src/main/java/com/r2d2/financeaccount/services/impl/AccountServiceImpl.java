package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.AccountDTO;
import com.r2d2.financeaccount.data.dto.TransactionDTO;
import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.repository.AccountRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) { this.accountRepository = accountRepository; }

    @Override
    public Account getById(Long accountId) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(NotFoundException::new);
        return account;
    }

    @Override
    public Set<Account> getAll(Long personId) {
        Set<Account> accounts = new HashSet<>();
        accountRepository.findAll().iterator().forEachRemaining(accounts::add);
        return accounts;
    }

    @Override
    public Account create(Account newAccount) {
        final Account account = new Account();

        account.setAccountName(newAccount.getAccountName());
        account.setCreateDate(OffsetDateTime.now());
        account.setCurrency(newAccount.getCurrency());
        account.setDescription(newAccount.getDescription());
        account.setCurrentValue(BigDecimal.ZERO);

        Account savedAccount = accountRepository.save(account);
        return account;

    }

    @Override
    public Account saveOrUpdate(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Account update(Long personId, Long accountId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void addTransaction(Long accountId, TransactionDTO txn) {

    }
}

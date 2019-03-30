package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.AccountDTO;
import com.r2d2.financeaccount.data.dto.AccountNewDTO;
import com.r2d2.financeaccount.data.dto.TransactionDTO;
import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.AccountRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    CurrencyService currencyService;
    PersonService personService;

    ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, CurrencyService currencyService,
                              PersonService personService, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.currencyService = currencyService;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDTO getById(Long accountId) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(NotFoundException::new);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public Set<AccountDTO> getAll(Long personId) {
        Set<Account> accounts = new HashSet<>();
        accountRepository.findAll().iterator().forEachRemaining(accounts::add);

        Set<AccountDTO> accountsDTO = new HashSet<>();

        for (Account a : accounts) {
            if(a.getOwner().getId() == personId)
                accountsDTO.add(modelMapper.map(a, AccountDTO.class));
        }

        return accountsDTO;
    }

    @Override
    public AccountDTO addAccount(Long personId, AccountNewDTO accountNewDTO) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);

        Account account = modelMapper.map(accountNewDTO, Account.class);
        account.setOwner(person);
        account.setBalance(BigDecimal.ZERO);
        account.setCreateDate(OffsetDateTime.now());

        person = person.addAccount(account);
        personService.saveOrUpdate(person);
        Account savedAccount = saveOrUpdate(account);
        return  modelMapper.map(savedAccount, AccountDTO.class);
    }

    @Override
    public Account saveOrUpdate(Account account) {
        Account a = accountRepository.save(account);
        return a;
    }

    @Override
    public AccountDTO update(Long accountId, AccountNewDTO accountNewDTO) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(NotFoundException::new);

        Account updatedAccount = modelMapper.map(accountNewDTO, Account.class);

        boolean updated = true;

        if(!(updatedAccount.getAccountName() == null)) {
            if (!account.getAccountName().equals(updatedAccount.getAccountName()))
                if (!updatedAccount.getAccountName().isEmpty()) {
                    account.setAccountName(updatedAccount.getAccountName());
                    updated = false;
                }
        }

        if(!(updatedAccount.getDescription() == null)) {
            if (!account.getDescription().equals(updatedAccount.getDescription())) {
                account.setAccountName(updatedAccount.getAccountName());
                updated = false;
            }
        }

        if(!(account.getCurrency() == null)) {
            if (!account.getCurrency().equals(updatedAccount.getCurrency())) {
                Currency currency = modelMapper.map(currencyService.getById(accountNewDTO.getCurrency().getCode()),
                        Currency.class);

                account.setCurrency(currency);
                Account savedAccount = saveOrUpdate(account);

                updated = false;
            }
        }

        if(!updated)
            saveOrUpdate(account);
        return  modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public void delete(Long id) {
        if(accountRepository.existsById(id))
            accountRepository.deleteById(id);
    }

    @Override
    public void removeFrom(Long personId, Long accountId) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);

        Account account = modelMapper.map(getById(accountId), Account.class);
        try {
            if(person.removeAccount(account)){
                delete(accountId);
                personService.saveOrUpdate(person);
            }
        }catch (Exception exp){
            exp.getStackTrace();
        }
    }

    @Override
    public void addTransaction(Long accountId, TransactionDTO txn) {
    }
}

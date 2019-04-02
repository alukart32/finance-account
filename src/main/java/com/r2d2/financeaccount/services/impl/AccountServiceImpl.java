package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.modelDTO.AccountDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountNewDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.TransactionDTO;
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
import org.springframework.transaction.annotation.Transactional;

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
    public CurrencyDTO getCurrency(Long accountId) {
        return modelMapper.map(accountRepository.findById(accountId).get().getCurrency(), CurrencyDTO.class);
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
    @Transactional
    public AccountDTO addAccount(Long personId, AccountNewDTO accountNewDTO) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);
        Account account = modelMapper.map(accountNewDTO, Account.class);

        if(accountNewDTO.getName() != null & accountNewDTO.getCurrency() != null) {
            if(accountRepository.count() > 0) {
                Account accountFromDb = accountRepository.findByName(account.getName()).orElse(null);
                if (accountFromDb != null) {
                    if (accountFromDb.getName().equals(account.getName())
                    & accountFromDb.getCurrency().equals(account.getCurrency()))
                        return modelMapper.map(accountFromDb, AccountDTO.class);
                }
            }
        }
        account.setBalance(BigDecimal.ZERO);
        account.setCreateDate(OffsetDateTime.now());

        person.addAccount(account);
        personService.saveOrUpdate(person);
        return modelMapper.map(saveOrUpdate(account), AccountDTO.class);
    }

    /**
     * Updating account (name, description, currency)
     *
     * @param accountId
     *        Target account
     *
     * @param accountNewDTO
     *        Possible new data for account
     *
     * @return  updated account
     */
    @Override
    public AccountDTO update(Long accountId, AccountNewDTO accountNewDTO) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(NotFoundException::new);

        Account updatedAccount = modelMapper.map(accountNewDTO, Account.class);

        boolean updated = true;

        if(updatedAccount.getName() != null) {
            if (!account.getName().equals(updatedAccount.getName()))
                if (!updatedAccount.getName().isEmpty()) {
                    account.setName(updatedAccount.getName());
                    updated = false;
                }
        }

        if(updatedAccount.getDescription() != null) {
            if (!account.getDescription().equals(updatedAccount.getDescription())) {
                account.setName(updatedAccount.getName());
                updated = false;
            }
        }

        if(account.getCurrency() != null) {
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

    /**
     * Remove(delete) account from person
     *
     * @param personId
     *        Person who has this account
     *
     * @param accountId
     *        Target account
     */
    @Override
    @Transactional
    public void removeFrom(Long personId, Long accountId) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);

        Account account = modelMapper.map(getById(accountId), Account.class);
        try {
                person.removeAccount(account);
                delete(accountId);
                personService.saveOrUpdate(person);
        }catch (Exception exp){
            exp.getStackTrace();
        }
    }

    @Override
    @Transactional
    public Account saveOrUpdate(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void addTransaction(Long accountId, TransactionDTO txn) {
    }
}

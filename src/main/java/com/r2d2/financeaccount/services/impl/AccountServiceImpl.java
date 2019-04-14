package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountNewDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyDTO;
import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.AccountRepository;
import com.r2d2.financeaccount.data.repository.TransactionRepository;
import com.r2d2.financeaccount.exception.ApiException;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import com.r2d2.financeaccount.utils.security.principal.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    private CurrencyService currencyService;
    private PersonService personService;

    private AuthService authService;

    private OrikaMapper mapper;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
                              CurrencyService currencyService, PersonService personService,
                              AuthService authService, OrikaMapper mapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.currencyService = currencyService;
        this.personService = personService;
        this.authService = authService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDTO getById(Long accountId) {
        Account account = getAccount(accountId, false);
        return mapper.map(account, AccountDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyDTO getCurrency(Long accountId) {
        return mapper.map(accountRepository.findByOwner(authService.getMyself()).getCurrency(), CurrencyDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AccountDTO> getAll(Long personId) {
        Set<Account> accounts = accountRepository.findAllByOwner(authService.getMyself());
        return mapper.mapAsSet(accounts, AccountDTO.class);
    }

    @Override
    @Transactional
    public AccountDTO addAccount(Long personId, AccountNewDTO accountNewDTO) {
        Person person = mapper.map(personService.getById(personId), Person.class);
        Account account = mapper.map(accountNewDTO, Account.class);

        if(accountNewDTO.getName() != null & accountNewDTO.getCurrency() != null) {
            if(accountRepository.count() > 0) {
                Account accountFromDb = accountRepository.findByName(account.getName()).orElse(null);
                if (accountFromDb != null) {
                    if (accountFromDb.getName().equals(account.getName())
                    & accountFromDb.getCurrency().equals(account.getCurrency()))
                        return mapper.map(accountFromDb, AccountDTO.class);
                }
            }
        }
        account.setBalance(BigDecimal.ZERO);
        account.setCreateDate(OffsetDateTime.now());

        person.addAccount(account);
        personService.saveOrUpdate(person);
        return mapper.map(save(account), AccountDTO.class);
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
        Account account = getAccount(accountId);

        Account updatedAccount = mapper.map(accountNewDTO, Account.class);

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
                Currency currency = mapper.map(currencyService.getById(accountNewDTO.getCurrency().getCode()),
                        Currency.class);

                account.setCurrency(currency);
                Account savedAccount = save(account);

                updated = false;
            }
        }

        if(!updated)
            save(account);
        return  mapper.map(account, AccountDTO.class);
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
        Person person = mapper.map(personService.getById(personId), Person.class);

        Account account = getAccount(accountId, false);
        try {
                person.removeAccount(account);
                delete(accountId);
                personService.saveOrUpdate(person);
        }catch (Exception exp){
            exp.getStackTrace();
        }
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    private Account getAccount(Long accountId) {
        return getAccount(accountId, true);
    }

    private Account getAccount(Long accountId, boolean needLock) {
        Account acc;
        String msg = "No account found [id = " + accountId + "]";
        if (needLock) {
            acc = accountRepository.findByIdForUpdate(accountId).orElseThrow(notFound(msg));
        } else {
            acc = accountRepository.findById(accountId).orElseThrow(notFound(msg));
        }

        //ensureMine(acc);
        return acc;
    }

    private Supplier<ApiException> notFound(String s) {
        return () -> new NotFoundException(s);
    }
}

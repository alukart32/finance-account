package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.AccountDTO;
import com.r2d2.financeaccount.data.dto.AccountNewDTO;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/account/")
public class AccountController {
    PersonService personService;
    AccountService accountService;
    CurrencyService currencyService;

    public AccountController(PersonService personService, AccountService accountService,
                             CurrencyService currencyService) {
        this.personService = personService;
        this.accountService = accountService;
        this.currencyService = currencyService;
    }

    @RequestMapping("{accountId}/show")
    public ResponseEntity<AccountDTO> show(@PathVariable String accountId){
        AccountDTO account = accountService.getById(Long.valueOf(accountId));
        return new ResponseEntity(account, HttpStatus.OK);
    }

    @RequestMapping("showAccountsFor/{personId}")
    public ResponseEntity<Set<AccountDTO>> showAccounts(@PathVariable String personId){
        Set<AccountDTO> accounts = accountService.getAll(Long.valueOf(personId));
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "createAccountFor/{personId}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public ResponseEntity<AccountDTO> addToPerson(@Valid @RequestBody AccountNewDTO accountNewDTO,
                                                @PathVariable String personId){
        AccountDTO accountDTO = accountService.addAccount(Long.valueOf(personId), accountNewDTO);
        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "{accountId}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFromPerson(@PathVariable String accountId,@PathVariable String personId){
        accountService.removeFrom( Long.valueOf(personId),Long.valueOf(accountId));
        return "redirect:/account/showAccountsFor/" + personId;
    }

    @RequestMapping(value = "{accountId}/update", method = RequestMethod.PUT)
    public ResponseEntity<AccountDTO> update(@RequestBody AccountNewDTO accountNewDTO,
                                             @PathVariable Long accountId){
        AccountDTO accountDTO = accountService.update(accountId, accountNewDTO);
        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }
}
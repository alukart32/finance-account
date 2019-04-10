package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.modelDTO.AccountDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountNewDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.DepositTxnDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.TransactionDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.WithdrawalTxnDTO;
import com.r2d2.financeaccount.exception.TxnBadRequestException;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping("{id}/show")
    public ResponseEntity<AccountDTO> show(@PathVariable("id") Long accountId){
        AccountDTO account = accountService.getById(accountId);
        return new ResponseEntity(account, HttpStatus.OK);
    }

    @RequestMapping("showAllFor/{id}")
    public ResponseEntity<Set<AccountDTO>> showAll(@PathVariable("id") Long personId){
        Set<AccountDTO> accounts = accountService.getAll(personId);
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "addTo/{id}", method = POST)
    @ResponseStatus(CREATED)
    public String addTo(@Valid @RequestBody AccountNewDTO accountNewDTO,
                                                @PathVariable("id") Long personId){
        AccountDTO accountDTO = accountService.addAccount(personId, accountNewDTO);
        return "redirect:/account/"+accountDTO.getId()+"/show";
    }

    @RequestMapping(value = "{id}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFrom(@PathVariable("id") Long accountId,@PathVariable Long personId){
        accountService.removeFrom(personId, accountId);
        return "redirect:/account/showAllFor/" + personId;
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String update(@RequestBody AccountNewDTO accountNewDTO,
                                             @PathVariable("id") Long accountId){
        accountService.update(accountId, accountNewDTO);
        return "redirect:/account/"+accountId+"/show";
    }
}
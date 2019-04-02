package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.modelDTO.AccountDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.AccountNewDTO;
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

    @RequestMapping("showAllFor/{personId}")
    public ResponseEntity<Set<AccountDTO>> showAll(@PathVariable String personId){
        Set<AccountDTO> accounts = accountService.getAll(Long.valueOf(personId));
        return new ResponseEntity(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "addTo/{personId}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public String addTo(@Valid @RequestBody AccountNewDTO accountNewDTO,
                                                @PathVariable String personId){
        AccountDTO accountDTO = accountService.addAccount(Long.valueOf(personId), accountNewDTO);
        return "redirect:/account/"+accountDTO.getId()+"/show";
    }

    @RequestMapping(value = "{accountId}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFrom(@PathVariable String accountId,@PathVariable String personId){
        accountService.removeFrom( Long.valueOf(personId),Long.valueOf(accountId));
        return "redirect:/account/showAllFor/" + personId;
    }

    @RequestMapping(value = "{accountId}/update", method = RequestMethod.PUT)
    public String update(@RequestBody AccountNewDTO accountNewDTO,
                                             @PathVariable Long accountId){
        accountService.update(accountId, accountNewDTO);
        return "redirect:/account/"+accountId+"/show";
    }
}
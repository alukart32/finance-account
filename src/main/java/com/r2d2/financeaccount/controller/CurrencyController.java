package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.CurrencyDTO;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency/")
public class CurrencyController {
    CurrencyService currencyService;
    AccountService accountService;
    PersonService personService;

    public CurrencyController(CurrencyService currencyService, AccountService accountService, PersonService personService) {
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.personService = personService;
    }

    @RequestMapping("showFor/{accountId}")
    public ResponseEntity<CurrencyDTO> showFor(@PathVariable String accountId) {
        CurrencyDTO currency = accountService.getCurrency(Long.valueOf(accountId));
        return new ResponseEntity(currency, HttpStatus.OK);
    }
}

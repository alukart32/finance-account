package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.CurrencyDTO;
import com.r2d2.financeaccount.services.service.CurrencyService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/currency/")
public class CurrencyController {

    CurrencyService currencyService;
    PersonService personService;

    public CurrencyController(CurrencyService currencyService, PersonService personService) {
        this.currencyService = currencyService;
        this.personService = personService;
    }

    @RequestMapping("/showCurrency/{id}")
    public ResponseEntity<CurrencyDTO> showCurrency(@PathVariable String id) {
        CurrencyDTO currency = currencyService.getById(id);
        return new ResponseEntity(currency, HttpStatus.OK);
    }


    @RequestMapping(value = "createCurrency", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public ResponseEntity<CurrencyDTO> createCurrency(@Valid @RequestBody CurrencyDTO currencyDTO){
        CurrencyDTO currency = currencyService.create(currencyDTO);
        return new ResponseEntity(currency, HttpStatus.OK);
    }
}

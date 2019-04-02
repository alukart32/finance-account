package com.r2d2.financeaccount.bootstrap;

import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.repository.CurrencyRepository;
import com.r2d2.financeaccount.services.service.CurrencyService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CurrencyBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    CurrencyRepository currencyRepository;

    public CurrencyBootstrap(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       currencyRepository.saveAll(getCurrencies());
    }

    private Set<Currency> getCurrencies(){
        Set<Currency> currencySet = new HashSet<>();

        Currency c1 = new Currency("rub","рубль");

        currencySet.add(c1);

        Currency c2 = new Currency("usd","доллар США");

        currencySet.add(c2);

        Currency c3 = new Currency("eur","евро");

        currencySet.add(c3);

        return currencySet;
    }
}

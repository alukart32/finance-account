package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyDTO;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.repository.CurrencyRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.CurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    CurrencyRepository currencyRepository;

    OrikaMapper mapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, OrikaMapper mapper) {
        this.currencyRepository = currencyRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyDTO getById(String currencyCode) {
        Currency currency = currencyRepository.findByCode(currencyCode).
                orElseThrow(NotFoundException::new);
        return mapper.map(currency, CurrencyDTO.class);
    }

    @Override
    public Set<CurrencyDTO> getAll() {
        return mapper.mapAsSet(currencyRepository.findAll(), CurrencyDTO.class);
    }

    @Override
    @Transactional
    public CurrencyDTO create(CurrencyDTO newCurrency) {
        final Currency currency = new Currency();

        currency.setCode(newCurrency.getCode());
        currency.setHumanReadableName(newCurrency.getHumanReadableName());

        Currency savedCurrency = saveOrUpdate(currency);

        return mapper.map(savedCurrency, CurrencyDTO.class);
    }

    @Override
    public CurrencyDTO update(String currencyCode, CurrencyDTO currency) {
        return null;
    }

    @Override
    public Currency saveOrUpdate(Currency currency) {
        Currency c = currencyRepository.save(currency);
        return c;
    }

    @Override
    public void remove(Long id) {
        currencyRepository.deleteById(id);
    }
}

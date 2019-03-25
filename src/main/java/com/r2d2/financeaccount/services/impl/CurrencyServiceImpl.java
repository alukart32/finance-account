package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.CurrencyDTO;
import com.r2d2.financeaccount.data.dto.CurrencyIdDTO;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.repository.CurrencyRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.CurrencyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    CurrencyRepository currencyRepository;
    ModelMapper modelMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CurrencyDTO getById(String currencyCode) {
        Currency currency = currencyRepository.findByCode(currencyCode).
                orElseThrow(NotFoundException::new);
        return modelMapper.map(currency, CurrencyDTO.class);
    }

    @Override
    public Set<CurrencyDTO> getAll() {
        Set<Currency> currencies = new HashSet<>();
        currencyRepository.findAll().iterator().forEachRemaining(currencies::add);

        Type setType = new TypeToken<Set<CurrencyDTO>>(){}.getType();
        return modelMapper.map(currencies, setType);
    }

    @Override
    public CurrencyDTO create(CurrencyDTO newCurrency) {
        final Currency currency = new Currency();

        currency.setCode(newCurrency.getCode());
        currency.setHumanReadableName(newCurrency.getHumanReadableName());

        Currency savedCurrency = saveOrUpdate(currency);

        return modelMapper.map(savedCurrency, CurrencyDTO.class);
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
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }
}

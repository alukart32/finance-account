package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.CurrencyDTO;
import com.r2d2.financeaccount.data.dto.CurrencyIdDTO;
import com.r2d2.financeaccount.data.model.Currency;

import java.util.Set;

public interface CurrencyService {
        CurrencyDTO getById(String currencyCode);

        Set<CurrencyDTO> getAll();

        CurrencyDTO create(CurrencyDTO newCurrency);

        CurrencyDTO update(String currencyCode, CurrencyDTO currency);

        Currency saveOrUpdate(Currency currency);

        void delete(Long id);
}
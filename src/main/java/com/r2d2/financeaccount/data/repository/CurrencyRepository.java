package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}

package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.ProfitSource;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfitSourceRepository extends CrudRepository<ProfitSource, Long> {
    Optional<ProfitSource> findByName(String name);
}

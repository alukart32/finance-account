package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

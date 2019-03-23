package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.CategoryDTO;
import com.r2d2.financeaccount.data.model.Category;

import java.util.Set;

public interface CategoryService {

    CategoryDTO getById(Long id);

    Set<Category> getAll();

    CategoryDTO saveOrUpdate(CategoryDTO categoryDTO);

    CategoryDTO update(Long personId, Long categoryId);
}
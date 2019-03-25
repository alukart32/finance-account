package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.CategoryDTO;
import com.r2d2.financeaccount.data.dto.CategoryIdDTO;
import com.r2d2.financeaccount.data.model.Category;

import java.util.Set;

public interface CategoryService {
    CategoryDTO getById(Long categoryId);

    Set<CategoryDTO> getAll(Long personId);

    CategoryDTO create(CategoryDTO newCategory);

    void addCategory(Long personId, CategoryIdDTO categoryIdDTO);

    Category saveOrUpdate(Category category);

    CategoryDTO update(Long categoryId, CategoryDTO categoryDTO);

    void delete(Long id);

}
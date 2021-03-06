package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.modelDTO.CategoryDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryNewDTO;
import com.r2d2.financeaccount.data.model.Category;

import java.util.Set;

public interface CategoryService {
    CategoryDTO getById(Long categoryId);

    Category getByName(String name);

    Set<CategoryDTO> getAll(Long personId);

    Set<CategoryDTO> getDefault();

    CategoryDTO addCategory(Long personId, CategoryNewDTO categoryNewDTO);

    Category saveOrUpdate(Category category);

    CategoryDTO update(Long categoryId, CategoryNewDTO categoryNewDTO);

    void delete(Long id);

    void removeFrom(Long personId, Long categoryId);

}
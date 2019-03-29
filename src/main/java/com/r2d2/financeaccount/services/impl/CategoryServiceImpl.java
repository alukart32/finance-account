package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.CategoryDTO;
import com.r2d2.financeaccount.data.dto.CategoryNewDTO;
import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.CategoryRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.CategoryService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    PersonService personService;

    ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, PersonService personService,
                               ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO getById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(NotFoundException::new);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public Set<CategoryDTO> getAll(Long personId) {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);

        Set<CategoryDTO> categoriesDTO = new HashSet<>();

        for (Category c : categories) {
            categoriesDTO.add(modelMapper.map(c, CategoryDTO.class));
        }

        return categoriesDTO;
    }

    @Override
    public CategoryDTO create(CategoryNewDTO newCategory) {
        final Category category =  modelMapper.map(newCategory, Category.class);
        Category savedCategory = saveOrUpdate(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public void addCategory(Long personId, CategoryNewDTO categoryNewDTO) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);
        Category category = modelMapper.map(categoryNewDTO, Category.class);

        person.addCategories(category);
        personService.saveOrUpdate(person);
    }

    @Override
    public Category saveOrUpdate(Category category) {
        Category c = categoryRepository.save(category);
        return c;
    }

    @Override
    public CategoryDTO update(Long categoryId, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryNewDTO;
import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.CategoryRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.CategoryService;
import com.r2d2.financeaccount.services.service.PersonService;
import com.r2d2.financeaccount.utils.security.core.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    PersonService personService;

    OrikaMapper mapper;

    @Autowired
    AuthService authService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, PersonService personService,
                               OrikaMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.personService = personService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(NotFoundException::new);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<CategoryDTO> getAll(Long personId) {
        Set<Category> categories = categoryRepository.findAllByOwner(null);
        categories.addAll(categoryRepository.findAllByOwner(authService.getMyself()));
        return mapper.mapAsSet(categories, CategoryDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<CategoryDTO> getDefault() {
        return mapper.mapAsSet(categoryRepository.findAllByOwner(null), CategoryDTO.class);
    }

    @Override
    @Transactional
    public CategoryDTO addCategory(Long personId, CategoryNewDTO categoryNewDTO) {
        Person person = mapper.map(personService.getById(personId), Person.class);

        Category category = mapper.map(categoryNewDTO, Category.class);

        if(categoryNewDTO.getName() != null) {
            if(categoryRepository.count() > 0) {
                Category categoryFromDb = categoryRepository.findByName(category.getName()).orElse(null);
                if (categoryFromDb != null) {
                    if (categoryFromDb.getDescription().equals(category.getDescription()))
                        return mapper.map(categoryFromDb, CategoryDTO.class);
                }
            }
        }
        personService.saveOrUpdate(person.addCategory(category));
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long categoryId, CategoryNewDTO categoryNewDTO) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(NotFoundException::new);

        boolean updated = true;

        if(!(category.getName().equals(categoryNewDTO.getName()))) {
            category.setName(categoryNewDTO.getName());
            updated = false;
        }

        if(!(category.getDescription().equals(categoryNewDTO.getDescription()))) {
            category.setDescription(categoryNewDTO.getDescription());
            updated = false;
        }

        if(!updated)
            saveOrUpdate(category);

        return mapper.map(category , CategoryDTO.class);
    }

/*
    private Category getCategory(Long categoryId) {
        return getCategory(categoryId, true);
    }

    private Category getCategory(Long categoryId, boolean needLock) {
        Category category;
        String msg = "No category found [id = " + categoryId + "]";
        if (needLock) {
            category = categoryRepository.findByIdForUpdate(categoryId).orElseThrow(notFound(msg));
        } else {
            category = categoryRepository.findById(categoryId).orElseThrow(notFound(msg));
        }

        return category;
    }

    private Supplier<ApiException> notFound(String s) {
        return () -> new NotFoundException(s);
    }
*/

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void removeFrom(Long personId, Long categoryId) {
        Person person = mapper.map(personService.getById(personId), Person.class);

        Category category = mapper.map(getById(categoryId), Category.class);
        try {
            person.removeCategory(category);
            delete(categoryId);
            personService.saveOrUpdate(person);
        }catch (Exception exp){
            exp.getStackTrace();
        }
    }
}

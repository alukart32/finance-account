package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.CategoryDTO;
import com.r2d2.financeaccount.data.dto.CategoryNewDTO;
import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.services.service.CategoryService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/category/")
public class CategoryController {
    CategoryService categoryService;
    PersonService personService;

    public CategoryController(CategoryService categoryService, PersonService personService) {
        this.categoryService = categoryService;
        this.personService = personService;
    }

    @RequestMapping("showAllFor/{personId}")
    public ResponseEntity<Set<CategoryDTO>> showCategories(@PathVariable String personId){
        Set<CategoryDTO> people = categoryService.getAll(Long.valueOf(personId));
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @RequestMapping("{categoryId}/show")
    public ResponseEntity<CategoryDTO> showCategory(@PathVariable String categoryId){
        CategoryDTO category = categoryService.getById(Long.valueOf(categoryId));
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryNewDTO categoryDTO){
        CategoryDTO category = categoryService.create(categoryDTO);
        return new ResponseEntity(category, HttpStatus.OK);
    }

   /* @RequestMapping(value = "{personId}/update", method = RequestMethod.PUT)
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonNewDTO personDTO,
                                            @PathVariable String personId) {
        PersonDTO person = personService.update(Long.valueOf(personId), personDTO);
        return new ResponseEntity(person, HttpStatus.OK);
    }*/
}


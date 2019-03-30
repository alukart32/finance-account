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

@RestController
@RequestMapping("/category/")
public class CategoryController {
    CategoryService categoryService;
    PersonService personService;

    public CategoryController(CategoryService categoryService, PersonService personService) {
        this.categoryService = categoryService;
        this.personService = personService;
    }

    @RequestMapping("showAllFor/{personId}")
    public ResponseEntity<Set<CategoryDTO>> showAll(@PathVariable String personId){
        Set<CategoryDTO> people = categoryService.getAll(Long.valueOf(personId));
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @RequestMapping("{categoryId}/show")
    public ResponseEntity<CategoryDTO> show(@PathVariable String categoryId){
        CategoryDTO category = categoryService.getById(Long.valueOf(categoryId));
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping(value = "addToPerson/{personId}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public ResponseEntity<CategoryDTO> addToPerson(@Valid @RequestBody CategoryNewDTO categoryDTO,
                                                           @PathVariable String personId){
        CategoryDTO category = categoryService.addCategory(Long.valueOf(personId), categoryDTO);
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping(value = "{categoryId}/deleteFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFromPerson(@PathVariable String categoryId, @PathVariable String personId){
        categoryService.removeFrom(Long.valueOf(personId), Long.valueOf(categoryId));
        return "redirect:/category/showAllFor/" + personId;
    }

    @RequestMapping(value = "{categoryId}/update", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryNewDTO categoryNewDTO,
                                            @PathVariable String categoryId) {
        CategoryDTO category = categoryService.update(Long.valueOf(categoryId), categoryNewDTO);
        return new ResponseEntity(category, HttpStatus.OK);
    }
}


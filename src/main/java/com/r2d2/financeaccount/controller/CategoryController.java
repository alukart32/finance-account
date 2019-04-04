package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.modelDTO.CategoryDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryNewDTO;
import com.r2d2.financeaccount.services.service.CategoryService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("{id}/show")
    public ResponseEntity<CategoryDTO> show(@PathVariable("id") Long categoryId){
        CategoryDTO category = categoryService.getById(categoryId);
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping("showAllFor/{id}")
    public ResponseEntity<Set<CategoryDTO>> showAll(@PathVariable("id") Long personId){
        Set<CategoryDTO> people = categoryService.getAll(personId);
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @RequestMapping(value = "addTo/{id}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public String addTo(@Valid @RequestBody CategoryNewDTO categoryNewDTO,
                                                           @PathVariable("id") Long personId){
        categoryService.addCategory(personId, categoryNewDTO);
        return "redirect:/category/showAllFor/"+personId;
    }

    @RequestMapping(value = "{id}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFrom(@PathVariable("id") Long categoryId, @PathVariable Long personId){
        categoryService.removeFrom(personId, categoryId);
        return "redirect:/category/showAllFor/" + personId;
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public String update(@Valid @RequestBody CategoryNewDTO categoryNewDTO,
                                            @PathVariable("id") Long categoryId) {
        categoryService.update(categoryId, categoryNewDTO);
        return "redirect:/category/"+categoryId+"/show";
    }
}
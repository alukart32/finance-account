package com.r2d2.financeaccount.controller.model;

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

    @RequestMapping("default")
    public ResponseEntity<Set<CategoryDTO>> getDefault(){
        Set<CategoryDTO> category = categoryService.getDefault();
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<CategoryDTO> get(@PathVariable("id") Long categoryId){
        CategoryDTO category = categoryService.getById(categoryId);
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping("getAll/{id}")
    public ResponseEntity<Set<CategoryDTO>> getAll(@PathVariable("id") Long personId){
        Set<CategoryDTO> categories = categoryService.getAll(personId);
        return new ResponseEntity(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "addTo/{id}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void addTo(@Valid @RequestBody CategoryNewDTO categoryNewDTO,
                                                           @PathVariable("id") Long personId){
        categoryService.addCategory(personId, categoryNewDTO);
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
        return "redirect:/category/"+categoryId;
    }
}
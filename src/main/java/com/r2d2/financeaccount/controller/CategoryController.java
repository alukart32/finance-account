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

    @RequestMapping("{categoryId}/show")
    public ResponseEntity<CategoryDTO> show(@PathVariable String categoryId){
        CategoryDTO category = categoryService.getById(Long.valueOf(categoryId));
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping("showAllFor/{personId}")
    public ResponseEntity<Set<CategoryDTO>> showAll(@PathVariable String personId){
        Set<CategoryDTO> people = categoryService.getAll(Long.valueOf(personId));
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @RequestMapping(value = "addTo/{personId}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public String addTo(@Valid @RequestBody CategoryNewDTO categoryNewDTO,
                                                           @PathVariable String personId){
        categoryService.addCategory(Long.valueOf(personId), categoryNewDTO);
        return "redirect:/category/showAllFor/"+personId;
    }

    @RequestMapping(value = "{categoryId}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFrom(@PathVariable String categoryId, @PathVariable String personId){
        categoryService.removeFrom(Long.valueOf(personId), Long.valueOf(categoryId));
        return "redirect:/category/showAllFor/" + personId;
    }

    @RequestMapping(value = "{categoryId}/update", method = RequestMethod.PUT)
    public String update(@Valid @RequestBody CategoryNewDTO categoryNewDTO,
                                            @PathVariable String categoryId) {
        categoryService.update(Long.valueOf(categoryId), categoryNewDTO);
        return "redirect:/category/"+categoryId+"/show";
    }
}
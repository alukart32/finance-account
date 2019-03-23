package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.services.service.CategoryService;
import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person/{personId}")
public class CategoryController {
    CategoryService categoryService;
    PersonService personService;
    /*
    CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryController(CategoryService categoryService, PersonService personService,
                              CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryService = categoryService;
        this.personService = personService;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @RequestMapping("/categories")
    public String getCategoriesByPersonId(@PathVariable String personId, Model model){
        model.addAttribute("person", personService.getById(Long.valueOf(personId)));
        return "person/categories/category-set";
    }

    @RequestMapping("/categories/{categoryId}")
    public String showCategoryByPersonIdAndCategoriesId(@PathVariable String personId, @PathVariable String categoryId,
                                                        Model model){
        model.addAttribute("tag", categoryService.
                findByPersonIdAndCategoryId(Long.valueOf(personId), Long.valueOf(categoryId)));
        return "person/categories/category-show";
    }

    @RequestMapping("/categories/{categoryId}/update")
    public String updateById(@PathVariable String personId, @PathVariable String categoryId, Model model) {
        model.addAttribute("tag", categoryService.findByPersonIdAndCategoryId(Long.valueOf(personId), Long.valueOf(categoryId)));
        return "/person/categories/update-category";
    }

    @PostMapping
    @RequestMapping("category")
    public String saveOrUpdate(@PathVariable String personId, @ModelAttribute CategoryCommand categoryCommand){
        CategoryCommand savedCommand = categoryService.saveOrUpdate(categoryCommand);
        return "redirect:/person/"+personId+"/categories/"+savedCommand.getId();
    }
    */
}


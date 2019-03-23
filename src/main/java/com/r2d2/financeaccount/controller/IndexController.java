package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.services.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    PersonService personService;

    public IndexController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("owners", personService.getAll());
        return "index";
    }
}

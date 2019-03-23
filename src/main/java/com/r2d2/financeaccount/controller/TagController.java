package com.r2d2.financeaccount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagController {
    /*
    TagService tagService;
    PersonService personService;
    TagToTagCommand tagToTagCommand;

    public TagController(TagService tagService, PersonService personService, TagToTagCommand tagToTagCommand) {
        this.tagService = tagService;
        this.personService = personService;
        this.tagToTagCommand = tagToTagCommand;
    }

    @RequestMapping("/tags")
    public String getTagsByPersonId(@PathVariable String personId, Model model){
        model.addAttribute("person", personService.findById(Long.valueOf(personId)));
        return "person/tags/tag-set";
    }

    @RequestMapping("/tags/{tagId}")
    public String showTagByPersonIdAndTagId(@PathVariable String personId, @PathVariable String tagId, Model model){
        model.addAttribute("tag", tagService.findByPersonIdAndTagId(Long.valueOf(personId), Long.valueOf(tagId)));
        return "person/tags/tag-show";
    }

    @RequestMapping("/tags/{tagId}/update")
    public String updateById(@PathVariable String personId, @PathVariable String tagId, Model model) {
        model.addAttribute("tag", tagService.findByPersonIdAndTagId(Long.valueOf(personId), Long.valueOf(tagId)));
        return "/person/tags/update-tag";
    }

    @PostMapping
    @RequestMapping("tag")
    public String saveOrUpdate(@PathVariable String personId, @ModelAttribute TagCommand tagCommand){
        TagCommand savedCommand = tagService.saveOrUpdate(tagCommand);
        return "redirect:/person/"+personId+"/tags/"+savedCommand.getId();
    }
    */
}

package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.TagDTO;
import com.r2d2.financeaccount.data.dto.TagNewDTO;
import com.r2d2.financeaccount.services.service.PersonService;
import com.r2d2.financeaccount.services.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/tag/")
public class TagController {

    TagService tagService;
    PersonService personService;

    public TagController(TagService tagService, PersonService personService) {
        this.tagService = tagService;
        this.personService = personService;
    }

    @RequestMapping("{tagId}/show")
    public ResponseEntity<TagDTO> show(@PathVariable String tagId){
        TagDTO tagDTO = tagService.getById(Long.valueOf(tagId));
        return new ResponseEntity(tagDTO, HttpStatus.OK);
    }

    @RequestMapping("showFor/{personId}")
    public ResponseEntity<Set<TagDTO>>showAllFor(@PathVariable String personId){
        Set<TagDTO> tagDTOs = tagService.getAll(Long.valueOf(personId));
        return new ResponseEntity(tagDTOs, HttpStatus.OK);
    }


    @RequestMapping(value = "addTo/{personId}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public String addTo(@Valid @RequestBody TagNewDTO tagNewDTO,
                        @PathVariable String personId){
        tagService.addTag(Long.valueOf(personId), tagNewDTO);
        return "redirect:/category/showAllFor/"+personId;
    }

    @RequestMapping(value = "{tagId}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFrom(@PathVariable String tagId, @PathVariable String personId){
        tagService.removeFrom(Long.valueOf(personId), Long.valueOf(tagId));
        return "redirect:/category/showAllFor/" + personId;
    }

    @RequestMapping("{tagId}/update")
    public String update(@Valid @RequestBody TagNewDTO tagNewDTO, @PathVariable String tagId) {
        tagService.update(Long.valueOf(tagId), tagNewDTO);
        return "redirect:/tag/"+tagId+"/show";
    }


}

package com.r2d2.financeaccount.controller.model;

import com.r2d2.financeaccount.data.dto.modelDTO.TagDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.TagNewDTO;
import com.r2d2.financeaccount.services.service.PersonService;
import com.r2d2.financeaccount.services.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("{id}")
    public ResponseEntity<TagDTO> get(@PathVariable("id") Long tagId){
        TagDTO tagDTO = tagService.getById(tagId);
        return new ResponseEntity(tagDTO, HttpStatus.OK);
    }

    @RequestMapping("getAll/{id}")
    public ResponseEntity<Set<TagDTO>> getAllFor(@PathVariable("id") Long personId){
        Set<TagDTO> tagDTOs = tagService.getAll(personId);
        return new ResponseEntity(tagDTOs, HttpStatus.OK);
    }


    @RequestMapping(value = "addTo/{id}", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void addTo(@Valid @RequestBody TagNewDTO tagNewDTO,
                        @PathVariable("id") Long personId){
        tagService.addTag(personId, tagNewDTO);
        /*return "redirect:/category/showAllFor/"+personId;*/
    }

    @RequestMapping(value = "{id}/removeFrom/{personId}", method = RequestMethod.DELETE)
    public String removeFrom(@PathVariable("id") Long tagId, @PathVariable Long personId){
        tagService.removeFrom(personId, tagId);
        return "redirect:/category/showAllFor/" + personId;
    }

    @RequestMapping("{id}/update")
    public String update(@Valid @RequestBody TagNewDTO tagNewDTO, @PathVariable("id") Long tagId) {
        tagService.update(tagId, tagNewDTO);
        return "redirect:/tag/"+tagId;
    }
}
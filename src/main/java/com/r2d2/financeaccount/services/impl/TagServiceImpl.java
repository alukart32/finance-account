package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.data.dto.modelDTO.TagDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.TagNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.model.Tag;
import com.r2d2.financeaccount.data.repository.TagRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.PersonService;
import com.r2d2.financeaccount.services.service.TagService;
import com.r2d2.financeaccount.utils.security.core.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;

    PersonService personService;

    @Autowired
    AuthService authService;

    OrikaMapper mapper;

    public TagServiceImpl(TagRepository tagRepository, PersonService personService, OrikaMapper mapper) {
        this.tagRepository = tagRepository;
        this.personService = personService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public TagDTO getById(Long tagId) {
        Tag tag = tagRepository.findById(tagId).
                orElseThrow(NotFoundException::new);
        return mapper.map(tag, TagDTO.class);

    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.findByName(name)
                .orElse(tagRepository.save(new Tag(name, authService.getMyself())));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<TagDTO> getAll(Long personId) {
        return mapper.mapAsSet(tagRepository.findAll(), TagDTO.class);
    }

    @Override
    @Transactional
    public TagDTO addTag(Long personId, TagNewDTO newTag) {
        Person person = mapper.map(personService.getById(personId), Person.class);

        Tag tag = mapper.map(newTag, Tag.class);

        if(newTag.getName() != null) {
            if(tagRepository.count() > 0) {
                Tag tagFromDb = tagRepository.findByName(tag.getName()).orElse(null);
                if (tagFromDb != null) {
                    if (tagFromDb.getName().equals(tag.getName()))
                        return mapper.map(tagFromDb, TagDTO.class);
                }
            }
        }
        personService.saveOrUpdate(person.addTag(tag));
        return mapper.map(tag, TagDTO.class);
    }

    @Override
    @Transactional
    public TagDTO update(Long tagId, TagNewDTO tagDTO) {
        Tag tag = tagRepository.findById(tagId).
                orElseThrow(NotFoundException::new);

        if(tagDTO.getName() != null){
            if(!tagDTO.getName().equals(tag.getName())) {
                tag.setName(tagDTO.getName());
                tagRepository.save(tag);
            }
        }
        return mapper.map(tag, TagDTO.class);
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) { tagRepository.deleteById(id);}

    @Override
    @Transactional
    public void removeFrom(Long personId, Long tagId) {
        Person person = mapper.map(personService.getById(personId), Person.class);

        try {
            person.removeTag(mapper.map(getById(tagId), Tag.class));
            delete(tagId);
            personService.saveOrUpdate(person);
        }catch (Exception exp){
            exp.getStackTrace();
        }
    }
}

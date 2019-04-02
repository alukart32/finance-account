package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.TagDTO;
import com.r2d2.financeaccount.data.dto.TagNewDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.model.Tag;
import com.r2d2.financeaccount.data.repository.TagRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.PersonService;
import com.r2d2.financeaccount.services.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;
    PersonService personService;

    ModelMapper modelMapper;

    public TagServiceImpl(TagRepository tagRepository, PersonService personService, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TagDTO getById(Long tagId) {
        Tag tag = tagRepository.findById(tagId).
                orElseThrow(NotFoundException::new);
        return modelMapper.map(tag, TagDTO.class);

    }

    @Override
    public Set<TagDTO> getAll(Long personId) {
        Set<Tag> tags = new HashSet<>();
        tagRepository.findAll().iterator().forEachRemaining(tags::add);

        Set<TagDTO> tagsDTO = new HashSet<>();

        for (Tag t : tags) {
            tagsDTO.add(modelMapper.map(t, TagDTO.class));
        }

        return tagsDTO;
    }

    @Override
    public TagDTO addTag(Long personId, TagNewDTO newTag) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);

        Tag tag = modelMapper.map(newTag, Tag.class);

        if(newTag.getName() != null) {
            if(tagRepository.count() > 0) {
                Tag tagFromDb = tagRepository.findByName(tag.getName()).orElse(null);
                if (tagFromDb != null) {
                    if (tagFromDb.getName().equals(tag.getName()))
                        return modelMapper.map(tagFromDb, TagDTO.class);
                }
            }
        }
        personService.saveOrUpdate(person.addTag(tag));
        return modelMapper.map(tag, TagDTO.class);
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
        return modelMapper.map(tag, TagDTO.class);
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) { tagRepository.deleteById(id);}

    @Override
    public void removeFrom(Long personId, Long tagId) {
        Person person = modelMapper.map(personService.getById(personId), Person.class);

        try {
            person.removeTag(modelMapper.map(getById(tagId), Tag.class));
            delete(tagId);
            personService.saveOrUpdate(person);
        }catch (Exception exp){
            exp.getStackTrace();
        }
    }
}

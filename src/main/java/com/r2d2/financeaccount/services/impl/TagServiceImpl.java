package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.dto.TagDTO;
import com.r2d2.financeaccount.data.model.Tag;
import com.r2d2.financeaccount.services.service.TagService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public TagDTO getById(String currencyCode) {
        return null;
    }

    @Override
    public Set<TagDTO> getAll() {
        return null;
    }

    @Override
    public TagDTO create(TagDTO newTag) {
        return null;
    }

    @Override
    public TagDTO update(Long tagId, TagDTO currency) {
        return null;
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void removeFrom(Long personId, Long tagId) {

    }
}

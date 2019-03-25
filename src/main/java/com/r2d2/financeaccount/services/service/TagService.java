package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.TagDTO;
import com.r2d2.financeaccount.data.model.Tag;

import java.util.Set;

public interface TagService {
    TagDTO getById(String currencyCode);

    Set<TagDTO> getAll();

    TagDTO create(TagDTO newTag);

    TagDTO update(Long tagId, TagDTO currency);

    Tag saveOrUpdate(Tag tag);

    void delete(Long id);
}

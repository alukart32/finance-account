package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.TagDTO;
import com.r2d2.financeaccount.data.dto.TagNewDTO;
import com.r2d2.financeaccount.data.model.Tag;

import java.util.Set;

public interface TagService {
    TagDTO getById(Long tagId);

    Set<TagDTO> getAll(Long personId);

    TagDTO addTag(Long personId, TagNewDTO newTag);

    TagDTO update(Long tagId, TagNewDTO tagDTO);

    Tag saveOrUpdate(Tag tag);

    void delete(Long id);

    void removeFrom(Long personId, Long tagId);
}

package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.TagDTO;
import com.r2d2.financeaccount.data.model.Tag;

import java.util.Set;

public interface TagService {
    TagDTO getById(Long id);

    Set<Tag> getAll();

    TagDTO saveOrUpdate(TagDTO tagDTO);

    TagDTO update(Long personId, Long tagId);

    void delete(Long id);
}

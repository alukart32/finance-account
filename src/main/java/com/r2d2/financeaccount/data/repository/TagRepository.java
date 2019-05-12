package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}


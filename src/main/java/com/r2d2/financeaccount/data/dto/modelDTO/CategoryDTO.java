package com.r2d2.financeaccount.data.dto.modelDTO;

import com.r2d2.financeaccount.data.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;

    private String name;

    private String description;
}
package com.r2d2.financeaccount.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryNewDTO {
    @NotEmpty
    @Length(max = 256)
    private String name;

    @Lob
    private String description;

}

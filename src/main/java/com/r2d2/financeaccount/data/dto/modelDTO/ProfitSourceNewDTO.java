package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ProfitSourceNewDTO {
    @NotEmpty
    @Length(max = 256)
    private String name;

    @Length(max = 256)
    private String description;
}

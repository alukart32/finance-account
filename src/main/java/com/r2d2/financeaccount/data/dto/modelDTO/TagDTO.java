package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TagDTO {
    @NotNull
    private Long id;

    private String name;
}

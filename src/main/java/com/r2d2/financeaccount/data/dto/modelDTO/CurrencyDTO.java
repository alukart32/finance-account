package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CurrencyDTO {
    @NotNull
    private String code;

    @NotEmpty
    private String humanReadableName;
}
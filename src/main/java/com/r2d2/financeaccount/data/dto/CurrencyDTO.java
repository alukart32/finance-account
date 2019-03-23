package com.r2d2.financeaccount.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CurrencyDTO {

    private String code;

    @NotNull
    private String humanReadableName;
}
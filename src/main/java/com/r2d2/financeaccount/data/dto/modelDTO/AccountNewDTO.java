package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountNewDTO {
    @NotEmpty
    @Length(max = 256)
    private String name;

    @Length(max = 10240)
    private String description;

    @NotNull
    private CurrencyIdDTO currency;
}
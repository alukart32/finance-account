package com.r2d2.financeaccount.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * When we need just code of accountCurrency
 */

@Getter
@Setter
public class CurrencyIdDTO {
    @NotEmpty
    private String code;
}
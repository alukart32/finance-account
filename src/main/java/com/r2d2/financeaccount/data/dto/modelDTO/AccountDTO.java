package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AccountDTO {
    private Long id;

    private String name;

    private String description;

    private CurrencyDTO currency;

    private OffsetDateTime createDate;

    private BigDecimal balance;
}
package com.r2d2.financeaccount.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountIdDTO {
    @NotNull
    private Long id;
}

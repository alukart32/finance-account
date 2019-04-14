package com.r2d2.financeaccount.data.dto.authDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LogInDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}


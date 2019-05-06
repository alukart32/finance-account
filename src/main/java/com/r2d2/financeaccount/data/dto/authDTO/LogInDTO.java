package com.r2d2.financeaccount.data.dto.authDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LogInDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}


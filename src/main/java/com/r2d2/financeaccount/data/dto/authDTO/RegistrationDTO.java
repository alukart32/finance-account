package com.r2d2.financeaccount.data.dto.authDTO;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegistrationDTO {

    @NotNull
    @Length(min = 1, max = 40)
    private String username;
    @NotNull
    @Length(max = 256)
    private String password;
    @NotNull
    @Length(max = 256)
    private String fullName;
}

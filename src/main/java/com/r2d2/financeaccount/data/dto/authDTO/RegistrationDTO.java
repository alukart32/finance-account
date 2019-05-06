package com.r2d2.financeaccount.data.dto.authDTO;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegistrationDTO {

    @NotBlank
    @Length(min = 1, max = 40)
    private String username;

    @NotBlank
    @Length(max = 256)
    private String firstName;

    @NotBlank
    @Length(max = 256)
    private String secondName;

    @NotBlank
    @Length(max = 256)
    private String password;

    @Length(max = 256)
    private String email;


}

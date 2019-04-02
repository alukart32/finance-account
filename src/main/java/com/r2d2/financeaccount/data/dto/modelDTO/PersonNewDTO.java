package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PersonNewDTO {
    @NotEmpty
    @Length(min = 1, max = 40)
    private String userName;

    @Length(max = 128)
    private String firstName;

    @Length(max = 128)
    private String secondName;
}

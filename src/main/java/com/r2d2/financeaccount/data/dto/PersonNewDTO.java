package com.r2d2.financeaccount.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

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

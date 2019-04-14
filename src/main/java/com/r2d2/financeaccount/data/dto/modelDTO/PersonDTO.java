package com.r2d2.financeaccount.data.dto.modelDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PersonDTO {
    private Long id;

    private String userName;

    private String firstName;

    private String secondName;

    private String email;

    private OffsetDateTime registerDate;
}

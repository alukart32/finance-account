package com.r2d2.financeaccount.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PersonDTO {
    private Long id;

    private String userName;

    private String fullName;

    private OffsetDateTime registerDate;

    private Set<CategoryDTO> categories = new HashSet<>();

    private Set<AccountDTO> accounts = new HashSet<>();

    private Set<TagDTO> tags = new HashSet<>();
}

package com.r2d2.financeaccount.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ProfitSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 1, max = 256)
    private String name;

    @NotNull
    @Length(max = 256)
    private String description;

    public ProfitSource() {}

    public ProfitSource(@NotNull @Length(min = 1, max = 256) String name, @NotNull @Length(max = 256) String description) {
        this.name = name;
        this.description = description;
    }
}

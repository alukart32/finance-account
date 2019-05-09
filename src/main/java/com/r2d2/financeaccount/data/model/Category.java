package com.r2d2.financeaccount.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(exclude = {"owner"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person owner;

    @NotNull
    @Length(min = 1, max = 256)
    private String name;

    @NotNull
    @Length(max = 256)
    private String description;

    public Category() {}

    public Category(@NotNull @Length(min = 1, max = 256) String name, @NotNull @Length(max = 256) String description) {
        this.name = name;
        this.description = description;
    }

    public Category(@NotNull @Length(min = 1, max = 256) String name, @NotNull @Length(max = 256) String description,
                    Person owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }
}

package com.r2d2.financeaccount.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(exclude = {"owner"})
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 64)
    private String name;

    @ManyToOne
    private Person owner;

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    public Tag(@NotNull @Length(max = 64) String name, Person owner) {
        this.name = name;
        this.owner = owner;
    }
}

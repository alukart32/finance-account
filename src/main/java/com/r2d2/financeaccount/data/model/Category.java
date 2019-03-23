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
    @Length(max = 1024)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id != null ? id.equals(category.id) : category.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Category() {}

    public Category(@NotNull @Length(min = 1, max = 256) String name, @NotNull @Length(max = 1024) String description) {
        this.name = name;
        this.description = description;
    }
}

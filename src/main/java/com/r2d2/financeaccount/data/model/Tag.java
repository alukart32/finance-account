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
    @Length(min = 1, max = 64)
    private String name;

    @ManyToOne
    private Person owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return id != null ? id.equals(tag.id) : tag.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Tag() {
    }

    public Tag(@NotNull @Length(min = 1, max = 64) String name) {
        this.name = name;
    }
}

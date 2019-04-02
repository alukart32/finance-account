package com.r2d2.financeaccount.data.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Length(min = 1, max = 40)
    private String userName;

    @Length(max = 128)
    private String firstName;

    @Length(max = 128)
    private String secondName;

    @Length(max = 128)
    private String password;

    private OffsetDateTime registerDate;

    @OneToMany(mappedBy = "owner")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Tag> tags = new HashSet<>();

    public Person addAccount(Account account) {
        account.setOwner(this);
        accounts.add(account);
        return this;
    }

    public Person removeAccount(Account account) {
        if (!accounts.isEmpty()) {
            accounts.remove(account);
        }
        return this;
    }

    public Person addCategory(Category category) {
        category.setOwner(this);
        categories.add(category);
        return this;
    }

    public Person removeCategory(Category category) {
        if (!categories.isEmpty()) {
            categories.remove(category);
        }
        return this;
    }

    public Person addTag(Tag tag) {
        tag.setOwner(this);
        tags.add(tag);
        return this;
    }

    public Person removeTag(Tag tag) {
        if (!tags.isEmpty()) {
            tags.remove(tag);
        }
        return this;
    }
}

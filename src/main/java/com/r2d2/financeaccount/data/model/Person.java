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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany( mappedBy = "owner")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Tag> tags = new HashSet<>();

    public Person addAccounts(Account account) {
        account.setOwner(this);
        accounts.add(account);
        return this;
    }


    public Person addCategories(Category category) {
        category.setOwner(this);
        categories.add(category);
        return this;
    }

    public Person addTags(Tag tag) {
        tag.setOwner(this);
        tags.add(tag);
        return this;
    }
}

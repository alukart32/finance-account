package com.r2d2.financeaccount.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Currency {
    @Id
    @Column(length = 3)
    private String code;

    @Column(length = 40)
    private String humanReadableName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountCurrency")
    private Set<Account> accounts = new HashSet<>();

    public Currency addAccounts(Account account) {
        account.setAccountCurrency(this);
        accounts.add(account);
        return this;
    }
}

package com.r2d2.financeaccount.data.model.txn;

import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.model.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"owner", "accountCurrency"})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    private Account account;

    @Getter
    @Setter
    @ManyToOne
    private Currency currency;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tag> tagSet = new HashSet<>();

    @Getter
    @Setter
    private BigDecimal newValue;

    @Getter
    @Setter
    @NotNull
    @Lob
    private String reason;

    @Getter
    @Setter
    @NotNull
    private OffsetDateTime createDate;
}
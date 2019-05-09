package com.r2d2.financeaccount.data.model.txn;

import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.model.Tag;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account src;

    @ManyToOne
    private Currency currency;

    @Positive
    private BigDecimal balance;

    @Positive
    private BigDecimal amount;

    @Lob
    private String reason;

    @NotNull
    private OffsetDateTime createDate;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tag> tagSet = new HashSet<>();
}
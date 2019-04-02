package com.r2d2.financeaccount.data.model.txn;

import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.Category;
import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.model.Tag;
import lombok.Data;
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
@Data
@EqualsAndHashCode(exclude = "owner")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tag> tagSet = new HashSet<>();

    private BigDecimal newValue;

    @NotNull
    @Lob
    private String reason;

    @NotNull
    private OffsetDateTime createDate;
}
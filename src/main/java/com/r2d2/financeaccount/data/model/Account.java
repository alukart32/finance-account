package com.r2d2.financeaccount.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@EqualsAndHashCode(exclude = {"owner", "currency"})
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Person owner;

    public Account() {}

    public Account(Currency currency, @Length(max = 256) String name, String description,
                   OffsetDateTime createDate, BigDecimal balance) {
        this.currency = currency;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.balance = balance;
    }

    @Length(max = 256)
    private String name;

    @Lob
    private String description;

    private OffsetDateTime createDate;

    private BigDecimal balance;
}

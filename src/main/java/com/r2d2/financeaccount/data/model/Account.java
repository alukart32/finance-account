package com.r2d2.financeaccount.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@EqualsAndHashCode(exclude = {"owner", "accountCurrency"})
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Currency accountCurrency;

    @ManyToOne
    private Person owner;

    public Account() {}

    public Account(Currency currency, @Length(max = 256) String accountName, String description,
                   OffsetDateTime createDate, BigDecimal currentValue) {
        this.accountCurrency = currency;
        this.accountName = accountName;
        this.description = description;
        this.createDate = createDate;
        this.currentValue = currentValue;
    }

    @Length(max = 256)
    private String accountName;

    @Lob
    private String description;

    private OffsetDateTime createDate;

    private BigDecimal currentValue;
}

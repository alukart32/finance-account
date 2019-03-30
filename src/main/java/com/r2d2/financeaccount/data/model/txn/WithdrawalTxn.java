package com.r2d2.financeaccount.data.model.txn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.r2d2.financeaccount.data.model.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"owner", "currency"})
@Entity
public class WithdrawalTxn extends Transaction {
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL)
    private Currency currency;
}

package com.r2d2.financeaccount.data.model.txn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.r2d2.financeaccount.data.model.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"owner", "accountCurrency"})
@Entity
public class DepositTxn extends Transaction {
    private BigDecimal amount;
}

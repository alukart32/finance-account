package com.r2d2.financeaccount.data.model.txn;

import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.data.model.ProfitSource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class DepositTxn extends Transaction {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProfitSource profitSource;
}

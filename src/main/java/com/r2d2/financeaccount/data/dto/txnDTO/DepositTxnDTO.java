package com.r2d2.financeaccount.data.dto.txnDTO;

import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyIdDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class DepositTxnDTO extends TransactionDTO {
    public DepositTxnDTO() {
        super();
        setType("deposit");
    }

    @Positive
    private BigDecimal amount;

    private CurrencyIdDTO currency;
}

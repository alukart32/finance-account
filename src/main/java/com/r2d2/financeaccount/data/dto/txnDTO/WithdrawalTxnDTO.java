package com.r2d2.financeaccount.data.dto.txnDTO;

import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyIdDTO;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class WithdrawalTxnDTO extends TransactionDTO {
    public WithdrawalTxnDTO() {
        super();
        setType("withdrawal");
    }

    @Positive
    private BigDecimal amount;

    private CurrencyIdDTO currency;
}

package com.r2d2.financeaccount.data.dto.txnDTO;

import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyIdDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawalTxnDTO extends TransactionDTO {
    public WithdrawalTxnDTO() {
        super();
        setType("withdrawal");
    }

    @Positive
    private BigDecimal amount;

    private CurrencyIdDTO currency;
}

package com.r2d2.financeaccount.data.dto.txnDTO;

import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyIdDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.ProfitSourceNewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class DepositTxnDTO extends TransactionDTO {
    public DepositTxnDTO() {
        super();
        setType("deposit");
    }

    @NotNull
    private ProfitSourceNewDTO profitSource;
}

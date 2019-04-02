package com.r2d2.financeaccount.data.dto.txnDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryDTO;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @Type(value = WithdrawalTxnDTO.class, name = "withdrawal"),
        @Type(value = DepositTxnDTO.class, name = "deposit")
})
public abstract class TransactionDTO {
    private Long id;

    private String type;

    @NotNull
    private String reason;

    private OffsetDateTime createDate;

    // [KN] Not required when de-serializing
    private BigDecimal value;

    private CategoryDTO category;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

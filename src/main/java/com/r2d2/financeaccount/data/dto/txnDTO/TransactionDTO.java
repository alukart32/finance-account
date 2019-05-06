package com.r2d2.financeaccount.data.dto.txnDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @Type(value = WithdrawalTxnDTO.class, name = "withdrawal"),
        @Type(value = DepositTxnDTO.class, name = "deposit")
})
@Getter
@Setter
public abstract class TransactionDTO {
    private Long id;

    private String type;

    private String reason;

    private OffsetDateTime createDate;

    // [KN] Not required when de-serializing
    private BigDecimal accountBalance;

    private CategoryDTO category;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionDTO(){}
}

package com.r2d2.financeaccount.data.dto.txnDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.r2d2.financeaccount.data.dto.modelDTO.CategoryDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.CurrencyIdDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.TagNewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private OffsetDateTime createDate;

    private CurrencyIdDTO currency;

    private TagNewDTO tag;

    @Positive
    // [KN] Not required when de-serializing
    private BigDecimal accountBalance;

    @Positive
    private BigDecimal amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionDTO(){}
}

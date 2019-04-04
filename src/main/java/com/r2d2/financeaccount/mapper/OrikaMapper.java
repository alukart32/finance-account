package com.r2d2.financeaccount.mapper;

import com.r2d2.financeaccount.data.dto.txnDTO.DepositTxnDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.WithdrawalTxnDTO;
import com.r2d2.financeaccount.data.model.txn.DepositTxn;
import com.r2d2.financeaccount.data.model.txn.WithdrawalTxn;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class OrikaMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(WithdrawalTxn.class, WithdrawalTxnDTO.class).byDefault().toClassMap());
        factory.registerClassMap(factory.classMap(DepositTxn.class, DepositTxnDTO.class).byDefault().toClassMap());
        super.configure(factory);
    }
}

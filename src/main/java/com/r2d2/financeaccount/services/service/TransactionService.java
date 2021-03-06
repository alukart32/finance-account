package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.dto.txnDTO.DepositTxnDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.TransactionDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.WithdrawalTxnDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransactionDTO getById(Long transactionId);

    Page<TransactionDTO> getAll(Pageable pageable, Long accountId);

    TransactionDTO deposit(Long accountId, DepositTxnDTO txn);

    TransactionDTO withdrawal(Long accountId, WithdrawalTxnDTO txn);

    void cancel(Long accountId, Long txnId);

    void rollback();
}

package com.r2d2.financeaccount.services.service;

import com.r2d2.financeaccount.data.model.txn.Transaction;

public interface TransactionService {
    Transaction getTransactionById(Long transactionId);

//    TransactionCommand withdraw(Long accountId, TransactionCommand transactionCommand);

//    TransactionCommand deposit(Long accountId, TransactionCommand transactionCommand);
}

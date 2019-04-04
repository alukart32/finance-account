package com.r2d2.financeaccount.services.impl;

import com.r2d2.financeaccount.data.model.Currency;
import com.r2d2.financeaccount.exception.ApiException;
import com.r2d2.financeaccount.exception.TxnBadRequestException;
import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.data.dto.txnDTO.DepositTxnDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.TransactionDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.WithdrawalTxnDTO;
import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.txn.DepositTxn;
import com.r2d2.financeaccount.data.model.txn.Transaction;
import com.r2d2.financeaccount.data.model.txn.WithdrawalTxn;
import com.r2d2.financeaccount.data.repository.TransactionRepository;
import com.r2d2.financeaccount.exception.NotFoundException;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository<Transaction> transactionRepository;

    AccountService accountService;

    OrikaMapper mapper;

    public TransactionServiceImpl(TransactionRepository<Transaction> transactionRepository, AccountService accountService,
                                  OrikaMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @Override
    public TransactionDTO getById(Long transactionId) {
        return mapper.map(transactionRepository.findById(transactionId).
                orElseThrow(NotFoundException::new), TransactionDTO.class);
    }

    @Override
    public Page<TransactionDTO> getAll(Pageable pageable, Long accountId ) {
        Account account = mapper.map(accountService.getById(accountId), Account.class);
        Page<Transaction> transactions = transactionRepository.findAllByAccount(pageable, account);
        return transactions.map((Transaction t) -> mapper.map(t, TransactionDTO.class));
    }

    @Override
    @Transactional
    public TransactionDTO deposit(Long accountId, DepositTxnDTO txn) {
        try{
        Account account = mapper.map(accountService.getById(accountId),Account.class);

        DepositTxn transaction = mapper.map(txn, DepositTxn.class);
        if(account.getCurrency().getCode().equals(txn.getCurrency().getCode())) {

            transaction.setCreateDate(OffsetDateTime.now());
            transaction.setSrc(account);


            BigDecimal value = account.getBalance();
            BigDecimal amount = convertToCurrency(
                transaction.getAmount(),
                transaction.getCurrency(),
                account.getCurrency());

            BigDecimal newBalance = value.add(amount);
            account.setBalance(newBalance);
            transaction.setAccountBalance(newBalance);

            DepositTxn txnResult = transactionRepository.save(transaction);
            accountService.save(account);

            return mapper.map(txnResult, TransactionDTO.class);
        }
        else
            throw new ApiException("Different currency!!! DepositTxn, Account");
        }catch (ApiException e){
            rollback();
            throw new TxnBadRequestException("Something is wrong");
        }
    }

    @Override
    @Transactional
    public TransactionDTO withdrawal(Long accountId, WithdrawalTxnDTO txn) {
        try {
        Account account = mapper.map(accountService.getById(accountId),Account.class);

        WithdrawalTxn transaction = mapper.map(txn, WithdrawalTxn.class);
        if(account.getCurrency().getCode().equals(txn.getCurrency().getCode())) {

        transaction.setCreateDate(OffsetDateTime.now());
        transaction.setSrc(account);

        BigDecimal value = account.getBalance();

        BigDecimal amount = convertToCurrency(
                transaction.getAmount(),
                transaction.getCurrency(),
                account.getCurrency()
        );

        BigDecimal newBalance = value.subtract(amount);
        account.setBalance(newBalance);
        transaction.setAccountBalance(newBalance);

        WithdrawalTxn txnResult = transactionRepository.save(transaction);
        accountService.save(account);

        return mapper.map(txnResult, TransactionDTO.class);
        }
        else
            throw new ApiException("Different currency!!! DepositTxn, Account");
        }catch (ApiException e){
            rollback();
            throw new TxnBadRequestException("Something is wrong");
        }
    }

    private BigDecimal convertToCurrency(BigDecimal amount, Currency from, Currency to) {
        // FIXME add support of currency conversions
        return amount;
    }

    @Override
    @Transactional
    public void cancel(Long accountId, Long txnId) {
        transactionRepository.deleteById(mapper.map(accountService.getById(accountId), Account.class), txnId);
    }

    @Override
    public void rollback() {
        // FIXME to do something
    }
}

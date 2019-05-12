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
import com.r2d2.financeaccount.services.service.*;
import com.r2d2.financeaccount.utils.security.core.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository<Transaction> transactionRepository;

    AccountService accountService;

    @Autowired
    ProfitSourceService profitSourceService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    OrikaMapper mapper;

    @Autowired
    AuthService authService;

    public TransactionServiceImpl(TransactionRepository<Transaction> transactionRepository, AccountService accountService,
                                  OrikaMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDTO getById(Long transactionId) {
        return mapper.map(transactionRepository.findById(transactionId).
                orElseThrow(NotFoundException::new), TransactionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDTO> getAll(Pageable pageable, Long accountId ) {
        Account account = mapper.map(accountService.getById(accountId), Account.class);
        Page<Transaction> transactions = transactionRepository.findAllByAccount(pageable, account);
        return transactions.map((Transaction t) -> mapper.map(t, TransactionDTO.class));
    }

    @Override
    @Transactional
    public TransactionDTO deposit(Long accountId, DepositTxnDTO txn) {
        try{
        Account account = accountService.getByOwner(authService.getMyself(), accountId);

        DepositTxn txnResult = transactionRepository.save((DepositTxn) setTxn(txn, account));
        accountService.save(account);

        return mapper.map(txnResult, TransactionDTO.class);
        }catch (ApiException e){
            rollback();
            throw new TxnBadRequestException("Something is wrong");
        }
    }

    @Override
    @Transactional
    public TransactionDTO withdrawal(Long accountId, WithdrawalTxnDTO txn) {
        try {

        Account account = accountService.getByOwner(authService.getMyself(), accountId);

        WithdrawalTxn txnResult = transactionRepository.save((WithdrawalTxn) setTxn(txn, account));
        accountService.save(account);

        return mapper.map(txnResult, TransactionDTO.class);

        }catch (ApiException e){
            rollback();
            throw new TxnBadRequestException("Something is wrong");
        }
    }

    private Transaction setTxn(TransactionDTO txn, Account account){

        Transaction t = null;

        /**
         * false - withdrawal
         * true - deposit
         */
        boolean operation = false;

        if (txn instanceof DepositTxnDTO) {
            t = mapper.map(txn, DepositTxn.class);

            ((DepositTxn) t).setProfitSource(profitSourceService.getByName(((DepositTxnDTO) txn).getProfitSource().getName()));

            operation = true;
        }
        else if (txn instanceof WithdrawalTxnDTO) {
            t = mapper.map(txn, WithdrawalTxn.class);

            ((WithdrawalTxn) t).setCategory(categoryService.getByName(((WithdrawalTxnDTO) txn).getCategory().getName()));
        }

        if(account.getCurrency().getCode().equals(txn.getCurrency().getCode())) {
            Objects.requireNonNull(t).setCreateDate(OffsetDateTime.now());

            t.setSrc(account);

            BigDecimal balance = account.getBalance();

            BigDecimal amount = convertToCurrency(  t.getAmount(), t.getCurrency(), account.getCurrency());

            BigDecimal newBalance;

            if(txn.getTag().getName() != null)
                t.setTag(tagService.getByName(txn.getTag().getName()));

            if (operation)
               newBalance = balance.add(amount);
            else
                newBalance = balance.subtract(amount);

            account.setBalance(newBalance);

            t.setBalance(newBalance);
        }
        else
            throw new ApiException("Different currency!!! Txn, Account");
        return t;
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

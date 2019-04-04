package com.r2d2.financeaccount.controller;

import com.r2d2.financeaccount.data.dto.txnDTO.DepositTxnDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.TransactionDTO;
import com.r2d2.financeaccount.data.dto.txnDTO.WithdrawalTxnDTO;
import com.r2d2.financeaccount.data.model.txn.Transaction;
import com.r2d2.financeaccount.exception.TxnBadRequestException;
import com.r2d2.financeaccount.services.service.AccountService;
import com.r2d2.financeaccount.services.service.TransactionService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping("account/{id}/txn/")
public class TransactionController {
    AccountService accountService;
    TransactionService txnService;

    public TransactionController(AccountService accountService, TransactionService txnService) {
        this.accountService = accountService;
        this.txnService = txnService;
    }

    @RequestMapping("show/{txnId}")
    public ResponseEntity<TransactionDTO> show(@PathVariable("txnId") Long txnId) {
        return new ResponseEntity(txnService.getById(txnId), HttpStatus.OK);
    }

    @RequestMapping("showAll")
    public Page<TransactionDTO> showAll(Pageable pageable, @PathVariable("id") Long accountId) {
        return txnService.getAll(pageable, accountId);
    }

    @RequestMapping(value = "makeTxn", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO makeTxn(@Valid @RequestBody TransactionDTO txn,
                                                  @PathVariable("id") Long accountId) {
        if (txn instanceof DepositTxnDTO) {
            return txnService.deposit(accountId, (DepositTxnDTO) txn);
        } else if (txn instanceof WithdrawalTxnDTO) {
            return txnService.withdrawal(accountId, (WithdrawalTxnDTO) txn);
        }
        throw new TxnBadRequestException("Unsupported transaction type submitted");
    }

    @RequestMapping(value = "{txnId}/cancel")
    public String cancelTxn(@PathVariable("txnId") Long txnId, @PathVariable("id") Long accountId) {
        txnService.cancel(accountId, txnId);
        return "redirect:/account/"+accountId+"/txn/showAll";
    }
}

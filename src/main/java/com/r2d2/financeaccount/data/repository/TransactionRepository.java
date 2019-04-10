package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.data.model.Account;
import com.r2d2.financeaccount.data.model.txn.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface TransactionRepository<T extends Transaction>  extends PagingAndSortingRepository<T, Long> {
    @Query("from #{#entityName} tx where src = ?1 order by tx.createDate")
    Page<T> findAllByAccount(Pageable pageable, Account account);

    @Modifying
    @Query("delete from #{#entityName} tx where src = :account and tx.id = :id")
    void deleteById(@Param("account") Account account, @Param("id") Long txnId);
}

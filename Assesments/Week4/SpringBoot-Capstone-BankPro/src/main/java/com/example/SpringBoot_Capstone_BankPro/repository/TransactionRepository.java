package com.example.SpringBoot_Capstone_BankPro.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SpringBoot_Capstone_BankPro.domain.Account;
import com.example.SpringBoot_Capstone_BankPro.domain.Transaction;
import com.example.SpringBoot_Capstone_BankPro.domain.Transaction.TransactionType;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    Page<Transaction> findAllByAccount(Pageable pageable, Account account);
    Page<Transaction> findByTransactionType(TransactionType transactionType, Pageable pageable);
    Page<Transaction> findByAmountGreaterThan(Double amount, Pageable pageable);
    Page<Transaction> findByAccountAndTransactionType(Account account, TransactionType transactionType, Pageable pageable);
    
    @Query("SELECT t FROM Transaction t WHERE t.account = :account AND t.transactionDate BETWEEN :startDate AND :endDate")
    Page<Transaction> findByAccountAndDateRange(@Param("account") Account account, 
                                               @Param("startDate") LocalDateTime startDate, 
                                               @Param("endDate") LocalDateTime endDate, 
                                               Pageable pageable);
}

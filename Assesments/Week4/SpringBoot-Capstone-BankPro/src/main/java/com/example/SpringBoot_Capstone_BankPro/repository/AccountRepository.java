package com.example.SpringBoot_Capstone_BankPro.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBoot_Capstone_BankPro.domain.Account;
import com.example.SpringBoot_Capstone_BankPro.domain.Account.AccountType;
import com.example.SpringBoot_Capstone_BankPro.domain.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);
    Page<Account> findByCustomer(Customer customer, Pageable pageable);
    Page<Account> findByAccountType(AccountType accountType, Pageable pageable);
    Page<Account> findByBalanceGreaterThan(Double balance, Pageable pageable);
    
    @Modifying
    @Transactional
    void deleteByAccountNumber(String accountNumber);
}

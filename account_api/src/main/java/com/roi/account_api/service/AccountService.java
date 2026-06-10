package com.roi.account_api.service;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.roi.account_api.model.Account;
import com.roi.account_api.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int createAccount(String firstName, String lastName) {
        logger.debug("createAccount called");
        Account account = new Account(firstName, lastName);
        accountRepository.save(account);
        return account.getAccountNumber();
    }

    public BigDecimal getAccountBalance(int accountNumber) {
        logger.debug("getAccountBalance called for account: {}", accountNumber);
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    @Transactional
    public void deposit(int accountNumber, BigDecimal amount) {
        logger.debug("deposit called for account: {} with amount: {}", accountNumber, amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }
        
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }
}

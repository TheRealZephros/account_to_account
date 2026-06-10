package com.roi.account_api.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.roi.account_api.model.Account;
import com.roi.account_api.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Transactional
    public void transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount) {
        logger.debug("transfer called from account: {} to account: {} with amount: {}", fromAccountNumber, toAccountNumber, amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be positive");
        }

        if (fromAccountNumber == toAccountNumber) {
            throw new RuntimeException(
                "Cannot transfer to the same account"
            );
        }
        Account fromAccount = accountRepository
                .findById(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        Account toAccount = accountRepository
                .findById(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("To account not found"));
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds in from account");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        logger.debug("Transfer successful from account: {} to account: {} with amount: {}", fromAccountNumber, toAccountNumber, amount);
    }
}

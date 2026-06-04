package com.roi.account_api.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferServiceTests {
    
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransferService transferService;

    @Test
    void transferShouldMoveFundsBetweenAccounts() {
        int fromAccount = accountService.createAccount("Bob", "Builder");
        int toAccount = accountService.createAccount("Charlie", "Chocolate");
        
        accountService.deposit(fromAccount, new BigDecimal("200.00"));
        assert(accountService.getAccountBalance(fromAccount).compareTo(new BigDecimal("200.00")) == 0);

        transferService.transfer(fromAccount, toAccount, new BigDecimal("100.00"));
        assert(accountService.getAccountBalance(fromAccount).compareTo(new BigDecimal("100.00")) == 0);
        assert(accountService.getAccountBalance(toAccount).compareTo(new BigDecimal("100.00")) == 0);
    }

    @Test
    void transferShouldFailIfFromAccountHasInsufficientFunds() {
        int fromAccount = accountService.createAccount("Dave", "Developer");
        int toAccount = accountService.createAccount("Eve", "Engineer");
        accountService.deposit(fromAccount, new BigDecimal("50.00"));
        try {
            transferService.transfer(fromAccount, toAccount, new BigDecimal("100.00"));
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Insufficient funds in from account"));
        }
    }

    @Test
    void transferShouldFailIfFromAccountDoesNotExist() {
        int toAccount = accountService.createAccount("Frank", "Fisher");
        try {
            transferService.transfer(9999, toAccount, new BigDecimal("50.00"));
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("From account not found"));
        }
    }

    @Test
    void transferShouldFailIfToAccountDoesNotExist() {
        int fromAccount = accountService.createAccount("Grace", "Green");
        try {
            transferService.transfer(fromAccount, 9999, new BigDecimal("50.00"));
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("To account not found"));
        }
    }

    @Test
    void transferShouldFailIfAmountIsNegative() {
        int fromAccount = accountService.createAccount("Hank", "Hill");
        int toAccount = accountService.createAccount("Ivy", "Iglesias");
        try {
            transferService.transfer(fromAccount, toAccount, new BigDecimal("-10.00"));
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Transfer amount must be positive"));
        }
    }

    @Test
    void transferShouldFailIfAmountIsZero() {
        int fromAccount = accountService.createAccount("Jack", "Jackson");
        int toAccount = accountService.createAccount("Karen", "King");
        try {
            transferService.transfer(fromAccount, toAccount, BigDecimal.ZERO);
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Transfer amount must be positive"));
        }
    }

}

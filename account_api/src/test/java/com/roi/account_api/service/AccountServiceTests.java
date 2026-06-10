package com.roi.account_api.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @Test
    void createAccountWithIdenticalNames() {
        int account1 = accountService.createAccount("John", "Doe");
        int account2 = accountService.createAccount("John", "Doe");
        
        assertNotEquals(account1, account2, "Account numbers should be unique even for identical names");
    }

    @Test
    void accountBalanceShouldStartAtZero() {
        int accountNumber = accountService.createAccount("Jane", "Smith");
        assert(accountService.getAccountBalance(accountNumber).compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    void depositShouldIncreaseBalance() {
        int accountNumber = accountService.createAccount("Alice", "Johnson");
        accountService.deposit(accountNumber, new BigDecimal("100.00"));
        assert(accountService.getAccountBalance(accountNumber).compareTo(new BigDecimal("100.00")) == 0);
    }

    @Test
    void depositShouldFailIfAmountIsNegative() {
        int accountNumber = accountService.createAccount("Bob", "Brown");
        try {
            accountService.deposit(accountNumber, new BigDecimal("-50.00"));
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Deposit amount must be positive"));
        }
    }

    @Test
    void depositToNonExistentAccountShouldThrow() {
        try {
            accountService.deposit(9999, new BigDecimal("50.00"));
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Account not found"));
        }
    }

    @Test
    void getBalanceOfNonExistentAccountShouldThrow() {
        try {
            accountService.getAccountBalance(9999);
            assert(false); // Should not reach here
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Account not found"));
        }
    }
}

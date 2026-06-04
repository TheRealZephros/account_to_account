package com.roi.account_api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roi.account_api.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/test")
    public String test() {
        return "Test endpoint for accounts";
    }

    @RequestMapping("/create")
    public String createAccount() {
        accountService.createAccount();
        return "Account created successfully";
    }

    @RequestMapping("/deposit")
    public String depositMoney() {
        accountService.depositMoney();
        return "Money deposited successfully";
    }

    @RequestMapping("/transfer")
    public String transferMoney() {
        accountService.transferMoney();
        return "Money transferred successfully";
    }

    @RequestMapping("/balance")
    public int getAccountBalance(@PathVariable String accountNumber) {
        return accountService.getAccountBalance(accountNumber);
    }
}

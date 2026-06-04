package com.roi.account_api.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @PostConstruct
    public void init() {
        logger.info("AccountService bean initialized and running");
        System.out.println("AccountService bean initialized and running");
    }

    public void createAccount() {
        logger.info("createAccount called");
        System.out.println("This will create a new account in the database");
    }

    public void depositMoney() {
        System.out.println("This will deposit money into an account in the database");
    }

    public void transferMoney() {
        System.out.println("This will transfer money between accounts in the database");
    }

    public int getAccountBalance(String accountNumber) {
        System.out.println("This will return the balance for account: " + accountNumber);
        return 0; // Placeholder return value
    }






}

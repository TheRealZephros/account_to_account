package com.roi.account_api.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roi.account_api.dto.CreateAccountRequest;
import com.roi.account_api.dto.CreateAccountResponse;
import com.roi.account_api.dto.DepositRequest;
import com.roi.account_api.dto.DepositResponse;
import com.roi.account_api.dto.GetAccountBalanceResponse;
import com.roi.account_api.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<CreateAccountResponse> createAccount(
        @Valid @RequestBody CreateAccountRequest request) {
        logger.debug("createAccount endpoint called");
        try {
            int accountNumber = accountService.createAccount(request.firstName(), request.lastName());
            CreateAccountResponse response = new CreateAccountResponse( accountNumber );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            logger.error(
                "Error creating account",
                e
            );
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<GetAccountBalanceResponse> getAccountBalance(@PathVariable int accountNumber) {
        logger.debug(
            "getAccountBalance endpoint called for account: {}",
            accountNumber
        );
        try{
            BigDecimal balance = accountService.getAccountBalance(accountNumber);
            return ResponseEntity.ok(new GetAccountBalanceResponse(balance));
        } catch (RuntimeException e) {
            logger.error(
                "Error getting account balance for accountNumber {}",
                accountNumber,
                e
            );
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<DepositResponse> deposit(
        @PathVariable int accountNumber,
        @Valid @RequestBody DepositRequest request) {
        logger.debug(
            "deposit endpoint called for account: {} with amount: {}", 
            accountNumber, 
            request.amount()
        );
        try {
            accountService.deposit(accountNumber, request.amount());
            return ResponseEntity.ok(new DepositResponse( accountNumber, request.amount(), "success" ));
        } catch (RuntimeException e) {
            logger.error(
                "Error depositing to account: {}", 
                accountNumber,
                e
            );
            return ResponseEntity.notFound().build();
        }
    }
}

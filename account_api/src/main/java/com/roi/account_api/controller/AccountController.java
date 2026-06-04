package com.roi.account_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    // private final AccountService accountService;

    // public AccountController(AccountService accountService) {
    //     this.accountService = accountService;
    // }

    @RequestMapping("/test")
    public String test() {
        return "Test endpoint for accounts";
    }

}

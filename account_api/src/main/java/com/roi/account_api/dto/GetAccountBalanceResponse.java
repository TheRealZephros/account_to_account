package com.roi.account_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class GetAccountBalanceResponse {
    @NotNull
    private BigDecimal balance;

    public GetAccountBalanceResponse() {
    }

    public GetAccountBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}

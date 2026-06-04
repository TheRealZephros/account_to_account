package com.roi.account_api.dto;


public class GetAccountBalanceRequest {
    private int accountNumber;

    public GetAccountBalanceRequest() {
    }

    public GetAccountBalanceRequest(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}

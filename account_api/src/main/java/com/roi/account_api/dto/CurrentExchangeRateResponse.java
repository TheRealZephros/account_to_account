package com.roi.account_api.dto;

import java.math.BigDecimal;

public class CurrentExchangeRateResponse {
    private BigDecimal DKK;
    private BigDecimal USD;

    public CurrentExchangeRateResponse() {
    }

    public CurrentExchangeRateResponse(BigDecimal DKK, BigDecimal USD) {
        this.DKK = DKK;
        this.USD = USD;
    }

    // Getters and setters
    public BigDecimal getDKK() {
        return DKK;
    }

    public void setDKK(BigDecimal DKK) {
        this.DKK = DKK;
    }

    public BigDecimal getUSD() {
        return USD;
    }

    public void setUSD(BigDecimal USD) {
        this.USD = USD;
    }
}

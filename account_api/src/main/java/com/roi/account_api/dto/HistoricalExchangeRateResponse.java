package com.roi.account_api.dto;

import java.math.BigDecimal;

public class HistoricalExchangeRateResponse {
    private int year;
    private int month;
    private int day;
    private BigDecimal requested_dkk_amount;
    private BigDecimal historical_usd_amount;
    private BigDecimal current_usd_amount;

    public HistoricalExchangeRateResponse() {
    }

    public HistoricalExchangeRateResponse( int year, int month, int day, BigDecimal requestedDKKAmount, BigDecimal historicalUSDAmount, BigDecimal currentUSDAmount) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.requested_dkk_amount = requestedDKKAmount;
        this.historical_usd_amount = historicalUSDAmount;
        this.current_usd_amount = currentUSDAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public BigDecimal getRequested_dkk_amount() {
        return requested_dkk_amount;
    }

    public void setRequested_dkk_amount(BigDecimal requested_dkk_amount) {
        this.requested_dkk_amount = requested_dkk_amount;
    }

    public BigDecimal getHistorical_usd_amount() {
        return historical_usd_amount;
    }

    public void setHistorical_usd_amount(BigDecimal historical_usd_amount) {
        this.historical_usd_amount = historical_usd_amount;
    }

    public BigDecimal getCurrent_usd_amount() {
        return current_usd_amount;
    }

    public void setCurrent_usd_amount(BigDecimal current_usd_amount) {
        this.current_usd_amount = current_usd_amount;
    }
}

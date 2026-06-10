package com.roi.account_api.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ExternalHistoricalExchangeRateResponse {
    private String result;
    private String documentation;
    private String terms_of_use;
    private int year;
    private int month;
    private int day;
    private String base_code;
    private BigDecimal requested_amount;
    private Map<String, BigDecimal> conversion_amounts;

    public ExternalHistoricalExchangeRateResponse() {
    }

    // Getters and setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTerms_of_use() {
        return terms_of_use;
    }

    public void setTerms_of_use(String terms_of_use) {
        this.terms_of_use = terms_of_use;
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

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public BigDecimal getRequested_amount() {
        return requested_amount;
    }

    public void setRequested_amount(BigDecimal requested_amount) {
        this.requested_amount = requested_amount;
    }

    public Map<String, BigDecimal> getConversion_amounts() {
        return conversion_amounts;
    }

    public void setConversion_amounts(Map<String, BigDecimal> conversion_amounts) {
        this.conversion_amounts = conversion_amounts;
    }
}

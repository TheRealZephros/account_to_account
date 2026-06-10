package com.roi.account_api.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.roi.account_api.dto.CurrentExchangeRateResponse;
import com.roi.account_api.dto.HistoricalExchangeRateResponse;
import com.roi.account_api.helper.Helper;
import com.roi.account_api.service.ExchangeService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/exchange/")
public class ExchangeController {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("current/{amount}")
    public ResponseEntity<CurrentExchangeRateResponse> getCurrentExchangeRate(@PathVariable BigDecimal amount) {
        BigDecimal usdAmount;
        try{
            usdAmount = exchangeService.getCurrentExchangeRateDKKToUSD(amount);
        } catch (Exception e) {
            logger.error("Error getting exchange rate: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
        CurrentExchangeRateResponse response = new CurrentExchangeRateResponse(amount, usdAmount);
        return ResponseEntity.ok(response);
    }

    //Needs a paid API key. message from the website:
    // 💸 Options For Free Accounts
    // We offer a popular and reliable free exchange rates API if that's your preference.
    // You can get started by signing up for a Free Plan 
    // - this plan includes use of our Standard endpoint and Pair Conversion endpoint 
    // as well as the ability to switch base currency.
    @Validated
    @GetMapping("historical/{year}/{month}/{day}")
    public ResponseEntity<HistoricalExchangeRateResponse> getHistoricalExchangeRate(
        @PathVariable @Positive @Min(2005) @Max(2015) int year, 
        @PathVariable @Positive int month, 
        @PathVariable @Positive int day
    ) {
        BigDecimal usdCurrentAmount;
        BigDecimal usdHistoricalAmount;
        
        if (!Helper.isValidDate(year, month, day)){
            throw new IllegalArgumentException("Invalid date");
        }

        try{
            usdCurrentAmount = exchangeService.getCurrentExchangeRateDKKToUSD(new BigDecimal("100.00"));
        } catch (Exception e) {
            logger.error("Error getting exchange rate: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
        try{
            usdHistoricalAmount = exchangeService.getHistoricalExchangeRateDKKToUSD(year, month, day, new BigDecimal("100.00"));
        } catch (Exception e) {
            logger.error("Error getting exchange rate: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }

        HistoricalExchangeRateResponse response = new HistoricalExchangeRateResponse(
            year,
            month, 
            day,
            BigDecimal.valueOf(100),
            usdHistoricalAmount,
            usdCurrentAmount
        );

        return ResponseEntity.ok(response);
    }
}

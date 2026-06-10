package com.roi.account_api.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.roi.account_api.dto.CurrentExchangeRateResponse;
import com.roi.account_api.service.ExchangeService;

import org.springframework.http.ResponseEntity;
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
}

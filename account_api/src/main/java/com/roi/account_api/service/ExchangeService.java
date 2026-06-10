package com.roi.account_api.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.roi.account_api.dto.ExternalCurrentExchangeRateResponse;
import com.roi.account_api.dto.ExternalHistoricalExchangeRateResponse;

import tools.jackson.databind.ObjectMapper;

@Service
public class ExchangeService {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${exchange-rate.api.key}")
    private String apiKey;

    public BigDecimal getCurrentExchangeRateDKKToUSD(BigDecimal amount) throws Exception {
        logger.debug("getCurrentExchangeRateDKKToUSD called with amount: {}", amount);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                    "https://v6.exchangerate-api.com/v6/"
                    + apiKey
                    + "/pair/DKK/USD/"
                    + amount.toPlainString()))
                .GET()
                .build();
        
        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());
        

        if (response.statusCode() != 200) {
            logger.error("Failed to get exchange rate, status code: " + response.statusCode());
            throw new RuntimeException("Failed to get exchange rate");
        }

        ExternalCurrentExchangeRateResponse exchangeRateResponse =
                objectMapper.readValue(
                        response.body(),
                        ExternalCurrentExchangeRateResponse.class);

        return exchangeRateResponse.getConversion_result();
    }

    public BigDecimal getHistoricalExchangeRateDKKToUSD( int year, int month, int day, BigDecimal amount) throws Exception {
        logger.debug("getHistoricalExchangeRateDKKToUSD called");

        if (year == 2012) {
            throw new IllegalArgumentException("year 2012 not allowed");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                    "https://v6.exchangerate-api.com/v6/"
                    + apiKey
                    + "/history/DKK/"
                    + year
                    + "/"
                    + month
                    + "/"
                    + day
                    + "/"
                    + amount ))
                .GET()
                .build();
        logger.error("URL:\nhttps://v6.exchangerate-api.com/v6/"
                    + apiKey
                    + "/history/DKK/"
                    + year
                    + "/"
                    + month
                    + "/"
                    + day
                    + "/"
                    + amount);
        
        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());
        

        if (response.statusCode() != 200) {
            logger.error("Failed to get historical exchange rate, status code: " + response.statusCode());
            throw new RuntimeException("Failed to get exchange rate");
        }

        ExternalHistoricalExchangeRateResponse exchangeRateResponse =
                objectMapper.readValue(
                        response.body(),
                        ExternalHistoricalExchangeRateResponse.class);
                    
        return exchangeRateResponse.getConversion_amounts().getOrDefault("USD", null);
    }
}

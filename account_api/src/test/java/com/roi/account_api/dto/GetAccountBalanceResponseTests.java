package com.roi.account_api.dto;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class GetAccountBalanceResponseTests {

    private final jakarta.validation.Validator validator;

    public GetAccountBalanceResponseTests() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void getAccountBalanceResponseWithValidData() {
        GetAccountBalanceResponse response = new GetAccountBalanceResponse(new BigDecimal("100.00"));
        assert(response.balance().equals(new java.math.BigDecimal("100.00")));
    }

    @Test
    void getAccountBalanceResponseWithNullBalanceShouldFail() {
        BigDecimal balance = null;
        GetAccountBalanceResponse response = new GetAccountBalanceResponse(balance);
        var violations = validator.validate(response);
        assert(violations.size() == 1);
    }

    
}

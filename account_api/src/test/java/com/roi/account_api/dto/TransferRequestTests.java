package com.roi.account_api.dto;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class TransferRequestTests {
    private final jakarta.validation.Validator validator;

    public TransferRequestTests() {
        
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void transferRequestWithValidData() {
        TransferRequest request = new TransferRequest(123, 456, new BigDecimal("100.00"));
        assert(request.fromAccountNumber() == 123);
        assert(request.toAccountNumber() == 456);
        assert(request.amount().equals(new java.math.BigDecimal("100.00")));
    }

    @Test
    void transferRequestWithNullAmountShouldFail() {
        BigDecimal amount = null;
        TransferRequest request = new TransferRequest(123, 456, amount);
        var violations = validator.validate(request);
        assert(violations.size() == 1);
    }

    @Test
    void transferRequestWithNegativeAmountShouldFail() {
        TransferRequest request = new TransferRequest(123, 456, new BigDecimal("-10.00"));
        var violations = validator.validate(request);
        assert(violations.size() == 1);
    }

    @Test
    void transferRequestWithZeroAmountShouldFail() {
        TransferRequest request = new TransferRequest(123, 456, BigDecimal.ZERO);
        var violations = validator.validate(request);
        assert(violations.size() == 1);
    }
}

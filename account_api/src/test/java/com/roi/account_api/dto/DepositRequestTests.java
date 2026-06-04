package com.roi.account_api.dto;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class DepositRequestTests {
    
    private final jakarta.validation.Validator validator;

    public DepositRequestTests() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void depositRequestWithNullAmountShouldFail() {
        DepositRequest request = new DepositRequest();
        BigDecimal amount = null;
        request.setAmount(null);

        var violations = validator.validate(request);
        assert(violations.size() == 1);
    }

    @Test
    void depositRequestWithNegativeAmountShouldFail() {
        DepositRequest request = new DepositRequest();
        request.setAmount(new BigDecimal("-10.00"));
        var violations = validator.validate(request);
        assert(violations.size() == 1);
    }

    @Test
    void depositRequestWithZeroAmountShouldFail() {
        DepositRequest request = new DepositRequest();
        request.setAmount(BigDecimal.ZERO);
        var violations = validator.validate(request);
        assert(violations.size() == 1);
    }
}

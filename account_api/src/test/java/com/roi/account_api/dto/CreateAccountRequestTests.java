package com.roi.account_api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class CreateAccountRequestTests {

    private final jakarta.validation.Validator validator;

    public CreateAccountRequestTests() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void createAccountRequestWithValidData() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        assert(request.getFirstName().equals("John"));
        assert(request.getLastName().equals("Doe"));
    }

    @Test
    void createAccountRequestWithEmptyFirstNameShouldFail() {

        CreateAccountRequest request =
                new CreateAccountRequest("", "Doe");

        Set<ConstraintViolation<CreateAccountRequest>> violations =
                validator.validate(request);

        assertEquals(1, violations.size());

        ConstraintViolation<CreateAccountRequest> violation =
                violations.iterator().next();

        assertEquals("firstName",
                violation.getPropertyPath().toString());
    }
    
    @Test
    void createAccountRequestWithEmptyLastNameShouldFail() {
        CreateAccountRequest request =
                new CreateAccountRequest("John", "");

        Set<ConstraintViolation<CreateAccountRequest>> violations =
                validator.validate(request);

        assertEquals(1, violations.size());

        ConstraintViolation<CreateAccountRequest> violation =
                violations.iterator().next();

        assertEquals("lastName",
                violation.getPropertyPath().toString());
    }

}

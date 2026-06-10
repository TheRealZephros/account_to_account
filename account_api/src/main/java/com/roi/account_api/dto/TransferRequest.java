package com.roi.account_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransferRequest(
    int fromAccountNumber,

    int toAccountNumber,

    @NotNull
    @DecimalMin(
        value = "0.01",
        message = "Transfer amount must be greater than zero"
    )
    BigDecimal amount
) {}
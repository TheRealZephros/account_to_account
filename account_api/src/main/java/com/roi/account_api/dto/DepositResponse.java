package com.roi.account_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record DepositResponse(
    int accountNumber,
    @NotNull
    @DecimalMin("0.1")
    BigDecimal balance,
    String status
) {}
package com.roi.account_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record GetAccountBalanceResponse(
    @NotNull BigDecimal balance
) {}
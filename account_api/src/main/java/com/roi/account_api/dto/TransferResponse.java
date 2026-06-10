package com.roi.account_api.dto;

import java.math.BigDecimal;

public record TransferResponse(
    int fromAccountNumber,
    int toAccountNumber,
    BigDecimal amount,
    String status
) {}

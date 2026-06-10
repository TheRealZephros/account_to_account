package com.roi.account_api.dto;

import java.math.BigDecimal;

public record CurrentExchangeRateResponse(
    BigDecimal DKK,
    BigDecimal USD
) {}
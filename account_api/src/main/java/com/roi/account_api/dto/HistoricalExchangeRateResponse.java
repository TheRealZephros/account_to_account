package com.roi.account_api.dto;

import java.math.BigDecimal;

public record HistoricalExchangeRateResponse(
    int year,
    int month,
    int day,
    BigDecimal requested_dkk_amount,
    BigDecimal historical_usd_amount,
    BigDecimal current_usd_amount
) {}
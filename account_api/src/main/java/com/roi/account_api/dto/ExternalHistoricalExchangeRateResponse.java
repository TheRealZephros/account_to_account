package com.roi.account_api.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExternalHistoricalExchangeRateResponse(
    String result,
    String documentation,
    String terms_of_use,
    int year,
    int month,
    int day,
    String base_code,
    BigDecimal requested_amount,
    Map<String, BigDecimal> conversion_amounts
) {}
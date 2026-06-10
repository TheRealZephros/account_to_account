package com.roi.account_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(
    @NotBlank String firstName,
    @NotBlank String lastName
) {}
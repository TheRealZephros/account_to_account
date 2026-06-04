package com.roi.account_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateAccountRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

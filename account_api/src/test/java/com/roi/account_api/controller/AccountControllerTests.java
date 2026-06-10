package com.roi.account_api.controller;

import com.roi.account_api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @Test
    void createAccount_ShouldReturn201() throws Exception {

        when(accountService.createAccount("John", "Doe"))
                .thenReturn(1);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "firstName":"John",
                        "lastName":"Doe"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        "{\"accountNumber\":1}"));
    }

    @Test
    void createAccount_WhenServiceThrows_ShouldReturn500() throws Exception {

        when(accountService.createAccount("John", "Doe"))
                .thenThrow(new RuntimeException());

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "firstName":"John",
                        "lastName":"Doe"
                    }
                    """))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getAccountBalance_ShouldReturnBalance() throws Exception {

        when(accountService.getAccountBalance(1))
                .thenReturn(new BigDecimal("100.00"));

        mockMvc.perform(get("/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance")
                        .value(100.00));
    }

    @Test
    void getAccountBalance_WhenAccountMissing_ShouldReturn404()
            throws Exception {

        when(accountService.getAccountBalance(999))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/accounts/999/balance"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deposit_ShouldReturn200() throws Exception {

        doNothing()
                .when(accountService)
                .deposit(1, new BigDecimal("50.00"));

        mockMvc.perform(post("/accounts/1/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "amount":"50.00"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"accountNumber\":1,\"balance\":50.00,\"status\":\"success\"}"));
    }

    @Test
    void deposit_WhenAccountMissing_ShouldReturn404()
            throws Exception {

        doThrow(new RuntimeException())
                .when(accountService)
                .deposit(999, new BigDecimal("50.00"));

        mockMvc.perform(post("/accounts/999/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "amount":"50.00"
                    }
                    """))
                .andExpect(status().isNotFound());
    }
}
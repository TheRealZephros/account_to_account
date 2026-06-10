package com.roi.account_api.controller;

import com.roi.account_api.service.TransferService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransferService transferService;

    @Test
    void transfer_ShouldReturn200() throws Exception {

        doNothing()
                .when(transferService)
                .transfer(
                        1,
                        2,
                        new BigDecimal("50.00"));

        mockMvc.perform(post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "fromAccountNumber": 1,
                        "toAccountNumber": 2,
                        "amount": 50.00
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"fromAccountNumber\":1,\"toAccountNumber\":2,\"amount\":50.00,\"status\":\"success\"}"));
    }

    @Test
    void transfer_WhenServiceThrows_ShouldReturn500()
            throws Exception {

        doThrow(new RuntimeException("{\"fromAccountNumber\":1,\"toAccountNumber\":2,\"amount\":50.00,\"status\":\"failure\"}"))
                .when(transferService)
                .transfer(
                        1,
                        2,
                        new BigDecimal("50.00"));

        mockMvc.perform(post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "fromAccountNumber": 1,
                        "toAccountNumber": 2,
                        "amount": 50.00
                    }
                    """))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("{\"fromAccountNumber\":1,\"toAccountNumber\":2,\"amount\":50.00,\"status\":\"failure\"}"));
    }
}
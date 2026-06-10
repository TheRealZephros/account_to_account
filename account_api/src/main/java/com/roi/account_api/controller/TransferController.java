package com.roi.account_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roi.account_api.dto.TransferRequest;
import com.roi.account_api.dto.TransferResponse;
import com.roi.account_api.service.TransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("")
    public ResponseEntity<TransferResponse> transfer(
        @Valid @RequestBody TransferRequest request) {
        logger.debug("transfer endpoint called");
        try {
            transferService.transfer(
                request.fromAccountNumber(),
                request.toAccountNumber(),
                request.amount()
            );
            return ResponseEntity.ok(new TransferResponse(request.fromAccountNumber(), request.toAccountNumber(), request.amount(), "success"));
        } catch (RuntimeException e) {
            logger.error("Error during transfer from {} to {}",
                request.fromAccountNumber(),
                request.toAccountNumber(),
                e
            );
            return ResponseEntity.status(500).body(new TransferResponse(request.fromAccountNumber(), request.toAccountNumber(), request.amount(), "failure"));
        }
    }
}

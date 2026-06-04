package com.roi.account_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roi.account_api.dto.TransferRequest;
import com.roi.account_api.service.TransferService;

@RestController
@RequestMapping("/transfer/")
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        logger.debug("transfer endpoint called");
        try {
            transferService.transfer(
                request.getFromAccountNumber(),
                request.getToAccountNumber(),
                request.getAmount()
            );
            return ResponseEntity.ok("Transfer successful");
        } catch (RuntimeException e) {
            logger.error("Error during transfer: " + e.getMessage());
            return ResponseEntity.status(500).body("Transfer failed");
        }
    }
}

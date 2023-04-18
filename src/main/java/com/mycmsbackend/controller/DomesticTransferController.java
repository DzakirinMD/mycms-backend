package com.mycmsbackend.controller;

import com.mycmsbackend.service.DomesticTransferService;
import com.mycmsbackend.service.dto.DomesticTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Domestic Transfer Rest Controller
 */
@RestController
@RequestMapping("api/v1/domestic-transfer")
public class DomesticTransferController {

    private final DomesticTransferService domesticTransferService;

    @Autowired
    public DomesticTransferController(DomesticTransferService domesticTransferService) {
        this.domesticTransferService = domesticTransferService;
    }

    @GetMapping
    public ResponseEntity<DomesticTransferDTO[]> getAllDomesticTransfer() {
        return ResponseEntity.ok(domesticTransferService.getAllDomesticTransfer());
    }

    @PostMapping
    public void createDomesticTransfer(@RequestBody DomesticTransferDTO domesticTransferDTO) {
        domesticTransferService.createDomesticTransfer(domesticTransferDTO);
    }
}

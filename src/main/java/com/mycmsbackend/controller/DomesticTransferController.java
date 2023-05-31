package com.mycmsbackend.controller;

import com.mycmsbackend.service.DomesticTransferService;
import com.mycmsbackend.service.dto.DomesticTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * Retrieves all domestic transfers from the domestic transfer microservice.
     * @return a ResponseEntity containing an array of DomesticTransferDTO objects representing all domestic transfers.
     */
    @GetMapping
    public ResponseEntity<DomesticTransferDTO[]> getAllDomesticTransfer() {
        return ResponseEntity.ok(domesticTransferService.getAllDomesticTransfer());
    }

    /**
     * Retrieves a single domestic transfer from the domestic transfer microservice by its transaction ID.
     * @param domesticTransferTrxId a String representing the transaction ID of the domestic transfer to retrieve.
     * @return a ResponseEntity containing a DomesticTransferDTO object representing the requested domestic transfer.
     */
    @GetMapping("/{domesticTransferTrxId}")
    public ResponseEntity<DomesticTransferDTO> getSinglelDomesticTransfer(@PathVariable String domesticTransferTrxId) {
        return ResponseEntity.ok(domesticTransferService.getSinglelDomesticTransfer(domesticTransferTrxId));
    }

    /**
     * Creates a new domestic transfer.
     * @param domesticTransferDTO a DomesticTransferDTO object representing the domestic transfer to create.
     */
    @PostMapping
    public void createDomesticTransfer(@RequestBody DomesticTransferDTO domesticTransferDTO) {
        domesticTransferService.createDomesticTransfer(domesticTransferDTO);
    }
}

package com.mycmsbackend.controller;

import com.mycmsbackend.service.InhouseTransferService;
import com.mycmsbackend.service.dto.InhouseTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1")
public class InhouseTransferController {

    private final InhouseTransferService inhouseTransferService;

    @Autowired
    public InhouseTransferController(InhouseTransferService inhouseTransferService) {
        this.inhouseTransferService = inhouseTransferService;
    }

    @PostMapping("/inhouse-transfer")
    public void createInhouseTransfer(@RequestBody InhouseTransferDTO inhouseTransferDTO) {
        inhouseTransferService.createInhouseTransfer(inhouseTransferDTO);
    }
}

package com.mycmsbackend.controller;

import com.mycmsbackend.kafka.producer.InhouseTransferProducer;
import com.mycmsbackend.domain.InhouseTransfer;
import com.mycmsbackend.service.dto.InhouseTransferEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1")
public class InhouseTransferController {

    private InhouseTransferProducer inhouseTransferProducer;

    @Autowired
    public InhouseTransferController(InhouseTransferProducer inhouseTransferProducer) {
        this.inhouseTransferProducer = inhouseTransferProducer;
    }

    @PostMapping("/inhouse-transfer")
    public String createInhouseTransfer(@RequestBody InhouseTransfer inhouseTransfer) {

        // TODO: check for null @RequestBody. If null setStatus to FAIL. else SUCCESS

        inhouseTransfer.setOrderId(UUID.randomUUID().toString());

        InhouseTransferEventDTO inhouseTransferEventDTO = new InhouseTransferEventDTO();
        inhouseTransferEventDTO.setStatus("CREATED");
        inhouseTransferEventDTO.setMessage("Inhouse transaction has been created. Your transaction id is : " + inhouseTransfer.getOrderId());
        inhouseTransferEventDTO.setInhouseTransfer(inhouseTransfer);

        inhouseTransferProducer.sendInhouseMessage(inhouseTransferEventDTO);

        return "Transaction has been created Successfully";
    }
}

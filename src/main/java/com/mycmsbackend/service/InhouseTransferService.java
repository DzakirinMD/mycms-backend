package com.mycmsbackend.service;

import com.mycmsbackend.kafka.producer.InhouseTransferProducer;
import com.mycmsbackend.service.dto.InhouseTransferDTO;
import com.mycmsbackend.service.dto.InhouseTransferEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InhouseTransferService {

    private final InhouseTransferProducer inhouseTransferProducer;

    @Autowired
    public InhouseTransferService(InhouseTransferProducer inhouseTransferProducer) {
        this.inhouseTransferProducer = inhouseTransferProducer;
    }

    public void createInhouseTransfer(InhouseTransferDTO inhouseTransferDTO) {

        inhouseTransferDTO.setOrderId(UUID.randomUUID().toString());

        InhouseTransferEventDTO inhouseTransferEventDTO = new InhouseTransferEventDTO();
        inhouseTransferEventDTO.setStatus("CREATED");
        inhouseTransferEventDTO.setMessage("Inhouse transaction has been created. Your transaction id is : " + inhouseTransferDTO.getOrderId());
        inhouseTransferEventDTO.setInhouseTransfer(inhouseTransferDTO);

        inhouseTransferProducer.sendInhouseMessage(inhouseTransferEventDTO);
    }
}

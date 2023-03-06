package com.mycmsbackend.service;

import com.mycmsbackend.kafka.producer.InhouseTransferProducer;
import com.mycmsbackend.service.dto.InhouseTransferDTO;
import com.mycmsbackend.service.dto.InhouseTransferEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class InhouseTransferService {

    private final InhouseTransferProducer inhouseTransferProducer;

    // microservice to microservice
    private final RestTemplate restTemplate;
    @Value("${my-cms.microservices.inhousetransfer}")
    private String inhouseTransferUrl;

    @Autowired
    public InhouseTransferService(InhouseTransferProducer inhouseTransferProducer, RestTemplate restTemplate) {
        this.inhouseTransferProducer = inhouseTransferProducer;
        this.restTemplate = restTemplate;
    }

    public void createInhouseTransfer(InhouseTransferDTO inhouseTransferDTO) {

        inhouseTransferDTO.setInhouseTransferTrxId(UUID.randomUUID().toString());

        InhouseTransferEventDTO inhouseTransferEventDTO = new InhouseTransferEventDTO();
        inhouseTransferEventDTO.setStatus("CREATED");
        inhouseTransferEventDTO.setMessage("Inhouse transaction has been created. Your transaction id is : " + inhouseTransferDTO.getInhouseTransferTrxId());
        inhouseTransferEventDTO.setInhouseTransferDTO(inhouseTransferDTO);

        inhouseTransferProducer.sendInhouseKafkaMessage(inhouseTransferEventDTO);
    }

    public ResponseEntity<InhouseTransferDTO[]> getAllInhouseTransfer() {
        return restTemplate.getForEntity(inhouseTransferUrl, InhouseTransferDTO[].class);
    }

    public ResponseEntity<InhouseTransferDTO> getSingleInhouseTransfer(String inhouseTransferTrxId) {
        return restTemplate.getForEntity(inhouseTransferUrl + inhouseTransferTrxId, InhouseTransferDTO.class);
    }
}

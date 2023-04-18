package com.mycmsbackend.service;

import com.mycmsbackend.kafka.producer.DomesticTransferProducer;
import com.mycmsbackend.service.dto.DomesticTransferDTO;
import com.mycmsbackend.service.dto.DomesticTransferEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class DomesticTransferService {

    @Value("${my-cms.microservices.domestictransfer}")
    private String domesticTransferUrl;

    // microservice to microservice
    private final RestTemplate restTemplate;

    private final DomesticTransferProducer domesticTransferProducer;


    @Autowired
    public DomesticTransferService(RestTemplate restTemplate, DomesticTransferProducer domesticTransferProducer) {
        this.restTemplate = restTemplate;
        this.domesticTransferProducer = domesticTransferProducer;
    }

    public DomesticTransferDTO[] getAllDomesticTransfer() {
        return restTemplate.getForEntity(domesticTransferUrl, DomesticTransferDTO[].class).getBody();
    }

    public void createDomesticTransfer(DomesticTransferDTO domesticTransferDTO) {
        domesticTransferDTO.setDomesticTransferTrxId(UUID.randomUUID().toString());

        DomesticTransferEventDTO domesticTransferEventDTO = new DomesticTransferEventDTO();
        domesticTransferEventDTO.setStatus("CREATED");
        domesticTransferEventDTO.setMessage("Domestic transfer is being processed. Your transaction id is : " + domesticTransferDTO.getDomesticTransferTrxId());
        domesticTransferEventDTO.setDomesticTransferDTO(domesticTransferDTO);

        domesticTransferProducer.sendDomesticTransferKafkaMessage(domesticTransferEventDTO);
    }
}

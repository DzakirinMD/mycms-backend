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

    /**
     * Gets all domestic transfers from the domestic transfer microservice.
     * @return an array of DomesticTransferDTO objects representing all domestic transfers.
     */
    public DomesticTransferDTO[] getAllDomesticTransfer() {
        return restTemplate.getForEntity(domesticTransferUrl, DomesticTransferDTO[].class).getBody();
    }

    /**
     * Gets a single domestic transfer from the domestic transfer microservice by its transaction ID.
     * @param domesticTransferTrxId a String representing the transaction ID of the domestic transfer to retrieve.
     * @return a DomesticTransferDTO object representing the requested domestic transfer.
     */
    public DomesticTransferDTO getSinglelDomesticTransfer(String domesticTransferTrxId) {
        return restTemplate.getForEntity(domesticTransferUrl + domesticTransferTrxId, DomesticTransferDTO.class).getBody();
    }

    /**
     * Creates a new domestic transfer and sends a message to the domestic transfer Kafka topic.
     * @param domesticTransferDTO a DomesticTransferDTO object representing the domestic transfer to create.
     */
    public void createDomesticTransfer(DomesticTransferDTO domesticTransferDTO) {
        domesticTransferDTO.setDomesticTransferTrxId(UUID.randomUUID().toString());

        DomesticTransferEventDTO domesticTransferEventDTO = new DomesticTransferEventDTO();
        domesticTransferEventDTO.setStatus("CREATED");
        domesticTransferEventDTO.setMessage("Domestic transfer is being processed. Your transaction id is : " + domesticTransferDTO.getDomesticTransferTrxId());
        domesticTransferEventDTO.setDomesticTransferDTO(domesticTransferDTO);

        domesticTransferProducer.sendDomesticTransferKafkaMessage(domesticTransferEventDTO);
    }
}

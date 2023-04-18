package com.mycmsbackend.kafka.producer;

import com.mycmsbackend.service.dto.DomesticTransferEventDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DomesticTransferProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomesticTransferProducer.class);

    private final NewTopic domesticTransferTopic;

    private final KafkaTemplate<String, DomesticTransferEventDTO> kafkaTemplate;

    @Autowired
    public DomesticTransferProducer(NewTopic domesticTransferTopic, KafkaTemplate<String, DomesticTransferEventDTO> kafkaTemplate) {
        this.domesticTransferTopic = domesticTransferTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDomesticTransferKafkaMessage(DomesticTransferEventDTO domesticTransferEventDTO) {

        LOGGER.info("Domestic-Transaction event sent => {}" , domesticTransferEventDTO);

        // create message
        Message<DomesticTransferEventDTO> message = MessageBuilder
                .withPayload(domesticTransferEventDTO)
                .setHeader(KafkaHeaders.TOPIC, domesticTransferTopic.name())
                .build();

        kafkaTemplate.send(message);
    }

}

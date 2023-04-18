package com.mycmsbackend.kafka.producer;

import com.mycmsbackend.service.dto.InhouseTransferEventDTO;
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
public class InhouseTransferProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InhouseTransferProducer.class);

    private final NewTopic inhouseTransferTopic;

    private final KafkaTemplate<String, InhouseTransferEventDTO> kafkaTemplate;

    @Autowired
    public InhouseTransferProducer(NewTopic inhouseTransferTopic, KafkaTemplate<String, InhouseTransferEventDTO> kafkaTemplate) {
        this.inhouseTransferTopic = inhouseTransferTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInhouseKafkaMessage(InhouseTransferEventDTO inhouseTransferEventDTO) {

        LOGGER.info("Inhouse-Transaction event sent => {}" , inhouseTransferEventDTO);

        // create message
        Message<InhouseTransferEventDTO> message = MessageBuilder
                .withPayload(inhouseTransferEventDTO)
                .setHeader(KafkaHeaders.TOPIC, inhouseTransferTopic.name())
                .build();

        kafkaTemplate.send(message);
    }

}

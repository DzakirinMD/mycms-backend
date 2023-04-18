package com.mycmsbackend.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.topic.domestic}")
    private String domesticTransferTopic;

    @Bean
    public NewTopic inhouseTransferTopic() {
        return TopicBuilder
                .name(topicName)
                .build();
    }

    @Bean
    public NewTopic domesticTransferTopic() {
        return TopicBuilder
                .name(domesticTransferTopic)
                .build();
    }
}

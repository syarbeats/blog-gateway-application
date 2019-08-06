package com.mitrais.cdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApplicationGatewayKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(ApplicationGatewayKafkaConsumer.class);
    private static final String TOPIC = "topic_applicationgateway";

    @KafkaListener(topics = "topic_applicationgateway", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}

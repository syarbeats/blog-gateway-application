package com.mitrais.cdc.web.rest;

import com.mitrais.cdc.service.ApplicationGatewayKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/application-gateway-kafka")
public class ApplicationGatewayKafkaResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationGatewayKafkaResource.class);

    private ApplicationGatewayKafkaProducer kafkaProducer;

    public ApplicationGatewayKafkaResource(ApplicationGatewayKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}

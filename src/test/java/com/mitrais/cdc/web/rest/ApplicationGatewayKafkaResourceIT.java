package com.mitrais.cdc.web.rest;

import com.mitrais.cdc.ApplicationGatewayApp;
import com.mitrais.cdc.service.ApplicationGatewayKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = ApplicationGatewayApp.class)
public class ApplicationGatewayKafkaResourceIT {

    @Autowired
    private ApplicationGatewayKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        ApplicationGatewayKafkaResource kafkaResource = new ApplicationGatewayKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/application-gateway-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}

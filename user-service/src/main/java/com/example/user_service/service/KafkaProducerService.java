package com.example.user_service.service;

import com.example.user_service.dto.ProductUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, ProductUpdateEvent> kafkaTemplate;
    private static final String TOPIC = "product-update-topic";

    public void sendProductUpdateEvent(ProductUpdateEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}

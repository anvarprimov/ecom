package com.example.product_service.service;

import com.example.product_service.dto.ProductUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final ProductService productService;
    private static final String TOPIC = "product-update-topic";
    private static final String GROUP_ID = "product-service-group";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consumeProductUpdateEvent(ProductUpdateEvent event) {
        productService.updateProductFromEvent(event);
    }
}

package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eureka", r -> r
                        .path("/eureka", "/eureka/")
                        .filters(f -> f.rewritePath("/eureka", "/"))
                        .uri("http://localhost:8761"))
                .route("eureka-static", r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761"))

                .route("product-service", r -> r
                        .path("/api/products", "/api/products/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .route("uesr-service", r -> r
                        .path("/api/users", "/api/users/**")
                        .uri("lb://USER-SERVICE"))
                .build();
    }
}

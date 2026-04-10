package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.Response;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Response<ProductResponseDto> create(ProductRequestDto requestDto) {
        return null;
    }

    public Response<ProductResponseDto> getOne(Long id) {
        return null;
    }

    public Page<ProductResponseDto> getAll(int page, int size, String sort) {
        return null;
    }

    public Response<ProductResponseDto> update(Long id, ProductRequestDto requestDto) {
        return null;
    }

    public Response<Object> softDelete(Long id) {
        return null;
    }
}

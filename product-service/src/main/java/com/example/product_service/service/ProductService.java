package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.Response;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Response<ProductResponseDto> create(ProductRequestDto requestDto) {
        return saveAndUpdate(new Product(), requestDto);
    }

    public Response<ProductResponseDto> getOne(Long id) {
        return Response.okData(toResponseDto(getActiveProductById(id)));
    }

    public Page<ProductResponseDto> getAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort));
        return productRepository.findAllByActiveTrue(pageable).map(this::toResponseDto);
    }

    public Response<ProductResponseDto> update(Long id, ProductRequestDto requestDto) {
        Product product = getActiveProductById(id);
        return saveAndUpdate(product, requestDto);
    }

    private Response<ProductResponseDto> saveAndUpdate(Product product, ProductRequestDto requestDto) {
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());

        Product savedProduct = productRepository.save(product);
        return Response.okData(toResponseDto(savedProduct));
    }

    public Response<Object> softDelete(Long id) {
        Product product = getActiveProductById(id);
        product.setActive(false);
        productRepository.save(product);
        return Response.ok();
    }

    private Product getActiveProductById(Long id) {
        return productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Product not found or inactive"));
    }

    private ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .brand(product.getBrand())
                .active(product.isActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}

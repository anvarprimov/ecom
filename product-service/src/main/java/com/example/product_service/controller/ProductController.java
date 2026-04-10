package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.Response;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public HttpEntity<Response<ProductResponseDto>> create(@Valid @RequestBody ProductRequestDto requestDto) {
        Response<ProductResponseDto> response = productService.create(requestDto);
        return ResponseEntity.status(response.isSuccess()? 201 : 400).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<Response<ProductResponseDto>> getOne(@PathVariable Long id) {
        Response<ProductResponseDto> response = productService.getOne(id);
        return ResponseEntity.status(response.isSuccess()? 200 : 404).body(response);
    }

    @GetMapping()
    public Page<ProductResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return productService.getAll(page, size, sort);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response<ProductResponseDto>> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDto requestDto) {
        Response<ProductResponseDto> response = productService.update(id, requestDto);
        return ResponseEntity.status(response.isSuccess()? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response<Object>> softDelete(@PathVariable Long id) {
        Response<Object> response = productService.softDelete(id);
        return ResponseEntity.status(response.isSuccess()? 204 : 409).body(response);
    }
}

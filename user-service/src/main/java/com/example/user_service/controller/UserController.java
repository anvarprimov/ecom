package com.example.user_service.controller;

import com.example.user_service.dto.*;
import com.example.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public HttpEntity<Response<UserResponseDto>> register(@Valid @RequestBody UserRequestDto userRequestDto) {
        Response<UserResponseDto> response = userService.register(userRequestDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/login")
    public HttpEntity<Response<UserResponseDto>> login(@Valid @RequestBody LoginDto loginDto) {
        Response<UserResponseDto> response = userService.login(loginDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return userService.getAll(page, size, sort);
    }
}

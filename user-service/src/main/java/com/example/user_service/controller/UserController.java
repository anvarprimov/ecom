package com.example.user_service.controller;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.Response;
import com.example.user_service.dto.UserRequestDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

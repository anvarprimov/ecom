package com.example.user_service.service;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.Response;
import com.example.user_service.dto.UserRequestDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Response<UserResponseDto> register(UserRequestDto userRequestDto) {
        if(userRepository.existsByEmail(userRequestDto.getEmail())) {
            return Response.fail("Email already exists");
        }

        User user = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();

        User saved = userRepository.save(user);

        return Response.okData(mapToResponse(saved));
    }

    private UserResponseDto mapToResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public Response<UserResponseDto> login(LoginDto loginDto) {
        return userRepository.findByEmail(loginDto.getEmail())
                .filter(user -> user.getPassword().equals(loginDto.getPassword()))
                .map(this::mapToResponse)
                .map(Response::okData)
                .orElseGet(() -> Response.fail("Invalid email or password"));
    }
}

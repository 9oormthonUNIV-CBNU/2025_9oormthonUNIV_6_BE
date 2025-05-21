package com.upbeat.upbeat.domain.user.controller;

import com.upbeat.upbeat.domain.user.dto.UserLoginRequestDto;
import com.upbeat.upbeat.domain.user.dto.UserSignupRequestDto;
import com.upbeat.upbeat.domain.user.dto.UserResponseDto;
import com.upbeat.upbeat.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto dto) {
        userService.signup(dto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginRequestDto dto) {
        UserResponseDto response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
}

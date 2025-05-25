package com.upbeat.upbeat.domain.user.controller;

import com.upbeat.upbeat.domain.user.dto.*;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "회원 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 합니다.")
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(@RequestBody @Valid UserSignupRequestDto dto) {
        return ResponseEntity.ok(userService.signup(dto));
    }

    @Operation(summary = "로그인", description = "사용자가 아이디와 비밀번호로 로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}

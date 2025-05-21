package com.upbeat.upbeat.domain.user.service;

import com.upbeat.upbeat.domain.user.dto.UserLoginRequestDto;
import com.upbeat.upbeat.domain.user.dto.UserSignupRequestDto;
import com.upbeat.upbeat.domain.user.dto.UserResponseDto;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import com.upbeat.upbeat.global.exception.CustomException;
import com.upbeat.upbeat.global.exception.type.ErrorCode;
import com.upbeat.upbeat.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(UserSignupRequestDto dto) {
        // 중복 사용자 아이디 체크
        if (userRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_USER_ID);
        }

        // User 엔티티 생성 및 저장
        User user = User.builder()
                .userId(dto.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .region(dto.getRegion())
                .nickname(dto.getNickname())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();

        userRepository.save(user);
    }

    public UserResponseDto login(UserLoginRequestDto dto) {
        // 사용자 조회, 없으면 예외
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 불일치시 예외
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 토큰 생성 및 DTO 반환
        String token = jwtUtil.generateToken(user.getUserId());

        return new UserResponseDto(user.getId(), user.getNickname(), token);
    }
}

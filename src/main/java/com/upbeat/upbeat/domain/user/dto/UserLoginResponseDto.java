package com.upbeat.upbeat.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "로그인 응답 DTO")
public class UserLoginResponseDto {
    @Schema(description = "유저 고유 ID", example = "1")
    private Long userId;

    @Schema(description = "유저 닉네임", example = "야호")
    private String nickname;

    @Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}

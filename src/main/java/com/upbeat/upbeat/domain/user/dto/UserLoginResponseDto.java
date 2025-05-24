package com.upbeat.upbeat.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponseDto {
    private Long userId;
    private String nickname;
    private String token;
}

package com.upbeat.upbeat.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {
    private String userId;
    private String password;
    private String region;
    private String nickname;
    private String phone;
    private String email;
}

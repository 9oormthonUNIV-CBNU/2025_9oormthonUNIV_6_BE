package com.upbeat.upbeat.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원가입 요청 DTO")
public class UserSignupRequestDto {

    @Schema(description = "아이디 (영문 + 숫자 4~20자)", example = "user2024")
    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,20}$",
            message = "아이디는 영문과 숫자를 포함한 4~20자여야 하며, 다른 문자는 포함될 수 없습니다."
    )
    private String userId;

    @Schema(description = "비밀번호 (영문 + 숫자 8~16자)", example = "password1")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$",
            message = "비밀번호는 영문과 숫자를 포함한 8~16자여야 하며, 특수문자는 사용할 수 없습니다."
    )
    private String password;

    @Schema(description = "닉네임", example = "야호")
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @Schema(description = "이메일", example = "user@example.com")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @Schema(description = "전화번호 (11자리)", example = "01012345678")
    @Pattern(regexp = "^\\d{11}$", message = "전화번호는 숫자 11자리여야 합니다.")
    private String phone;

    @Schema(description = "지역", example = "서울특별시 강남구")
    private String region;
}


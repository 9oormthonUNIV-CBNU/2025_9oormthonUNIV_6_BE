package com.upbeat.upbeat.domain.cardgame.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class AnswerRequestDto {
    @Schema(description = "답변 내용", example="저는 빠른 실행력과 추진력이 동기들보다 강점이라 자신있습니다.")
    @NotBlank(message = "답변 내용은 필수입니다.")
    @Pattern(regexp = "^.{0,30}$", message = "30자 이하로 입력해주세요.")
    private String content;
//    @Schema(description = "유저 Id")
//    @NotNull(message = "유저 Id는 필수입니다.")
//    private Long userId;
    @Schema(description = "카드 Id")
    @NotBlank(message = "카드 Id는 필수입니다.")
    private BigInteger cardId;
}

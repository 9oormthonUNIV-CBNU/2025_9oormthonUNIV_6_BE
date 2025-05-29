package com.upbeat.upbeat.domain.cardgame.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StrategyRequestDto {
    @Schema(description = "전략", example="공손하게 회피")
    @NotBlank(message = "전략은 필수입니다.")
    private String strategy;
//    @Schema(description = "유저 Id")
//    @NotNull(message = "유저 Id는 필수입니다.")
//    private Long userId;
    @Schema(description = "카드 Id")
    @NotNull(message = "카드 Id는 필수입니다.")
    private Long cardId;
}

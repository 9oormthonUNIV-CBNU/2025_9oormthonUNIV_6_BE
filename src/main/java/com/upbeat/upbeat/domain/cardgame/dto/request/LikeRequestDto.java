package com.upbeat.upbeat.domain.cardgame.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class LikeRequestDto {
    @Schema(description = "유저 Id")
    @NotNull(message = "유저 Id는 필수입니다.")
    private Long userId;
    @Schema(description = "카드 Id")
    @NotBlank(message = "카드 Id는 필수입니다.")
    private BigInteger answerId;
}

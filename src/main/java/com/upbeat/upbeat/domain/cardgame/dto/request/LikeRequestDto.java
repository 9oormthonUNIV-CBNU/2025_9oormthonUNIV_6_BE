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
public class LikeRequestDto {
    @Schema(description = "답변 Id")
    @NotNull(message = "답변 Id는 필수입니다.")
    private Long answerId;
}

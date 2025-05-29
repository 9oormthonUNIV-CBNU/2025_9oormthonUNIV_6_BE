package com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers;

import com.upbeat.upbeat.domain.cardgame.entity.Strategy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CSARDtosStrategyDto {
    @Schema(description = "전략 ID", example="1")
    private Long strategyId;
    @Schema(description = "전략", example="공손하게 회피")
    private String strategy;
    @Schema(description = "유저 ID", example="1")
    private Long userId;
    @Schema(description = "유저 이름", example="러키")
    private String userNickname;

    public static CSARDtosStrategyDto create(Strategy strategy) {
        return new CSARDtosStrategyDto(
                strategy.getId(),
                strategy.getStrategy().getDescription(),
                strategy.getUser().getId(),
                strategy.getUser().getNickname()
        );
    }
}

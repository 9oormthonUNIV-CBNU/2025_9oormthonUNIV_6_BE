package com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers;

import com.upbeat.upbeat.domain.cardgame.entity.Strategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CSARDtosStrategyDto {
    private BigInteger strategyId;
    private String strategy;
    private Long userId;
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

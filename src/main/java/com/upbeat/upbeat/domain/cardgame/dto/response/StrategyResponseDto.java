package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Strategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class StrategyResponseDto {
//    private BigInteger id;
//    private String strategy;
//    private Long userId;
//    private BigInteger cardId;
//    private Answer answer;
    private String message;

    public static StrategyResponseDto createStrategyDto(Strategy strategy) {
        return new StrategyResponseDto(
//                strategy.getId(),
//                strategy.getStrategy().getDescription(),
//                strategy.getUser().getId(),
//                strategy.getCard().getId(),
//                strategy.getAnswer()
                "전략이 등록되었습니다."
        );
    }
}

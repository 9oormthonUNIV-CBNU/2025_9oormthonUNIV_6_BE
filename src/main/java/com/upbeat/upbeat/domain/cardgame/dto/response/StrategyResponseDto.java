package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Strategy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StrategyResponseDto {
//    private Long id;
//    private String strategy;
//    private Long userId;
//    private Long cardId;
//    private Answer answer;
    @Schema(description = "메세지", example="전략이 등록되었습니다.")
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

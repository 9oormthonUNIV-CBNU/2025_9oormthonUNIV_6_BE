package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CardResponseDto {
    @Schema(description = "카드 ID", example="1")
    private Long cardId;
//    private String questionContent;
//    private String company;
//    private String job;
//    private List<Status> statusKeyword;
//    private String strategy;
//    private String answerContent;
//    private LocalDateTime createdAt;
//    private Long userId;
//    private List<Answer> answerList;
    @Schema(description = "메세지", example="질문 등록 완료")
    private String message;

    public static CardResponseDto createCardDto(Card card) {
        return new CardResponseDto(
                card.getId(),
                "질문 등록 완료"
        );
    }
}

package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Card;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CardResponseDto {
    private BigInteger cardId;
//    private String questionContent;
//    private String company;
//    private String job;
//    private List<Status> statusKeyword;
//    private String strategy;
//    private String answerContent;
//    private LocalDateTime createdAt;
//    private Long userId;
//    private List<Answer> answerList;
//    private List<CheckedCard> checkedCardList;
    private String message;

    public static CardResponseDto createCardDto(Card card) {
        return new CardResponseDto(
                card.getId(),
                "질문 등록 완료"
        );
    }
}

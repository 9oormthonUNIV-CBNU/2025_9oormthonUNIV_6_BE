package com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds;

import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.entity.Status;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CACRDtosCardDto {
    private BigInteger cardId;
    private String questionContent;
    private String companyName;
    private String job;
    private List<Status> status;
    private String strategy;
    private String answerContent;
    private LocalDateTime createdAt;
    private Long userId;
//    private List<Answer> answerList;
//    private List<CheckedCard> checkedCardList;

    public static CACRDtosCardDto create(Card card) {
        return new CACRDtosCardDto(
                card.getId(),
                card.getQuestionContent(),
                card.getCompany(),
                card.getJob(),
                card.getStatusKeyword(),
                card.getStrategy().getDescription(),
                card.getAnswerContent(),
                card.getCreatedAt(),
                card.getUser().getId()
        );
    }
}

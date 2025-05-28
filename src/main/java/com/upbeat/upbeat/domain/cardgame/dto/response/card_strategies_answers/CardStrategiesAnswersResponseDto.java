package com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers;

import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.entity.Status;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
public class CardStrategiesAnswersResponseDto {
    private BigInteger cardId;
    private String questionContent;
    private String company;
    private String job;
    private List<Status> status;
    private String strategy;
    private String answerContent;
    private LocalDateTime createdAt;
    private Long userId;

    private List<CSARDtosStrategyDto> strategies;
    private List<CSARDtosAnswerDto> answers;

    public CardStrategiesAnswersResponseDto(Card card, List<CSARDtosStrategyDto> strategies, List<CSARDtosAnswerDto> answers) {
        this.cardId = card.getId();
        this.questionContent = card.getQuestionContent();
        this.company = card.getCompany();
        this.job = card.getJob();
        this.status = card.getStatusKeyword();
        this.strategy = card.getStrategy().getDescription();
        this.answerContent = card.getAnswerContent();
        this.createdAt = card.getCreatedAt();
        this.userId = card.getUser().getId();

        this.strategies = strategies;
        this.answers = answers;
    }
}

package com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers;

import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardStrategiesAnswersResponseDto {
    @Schema(description = "카드 ID", example = "1")
    private Long cardId;
    @Schema(description = "질문 내용", example="동기들 중 본인이 가장 낫다고 생각한 점은 무엇인가요?")
    private String questionContent;
    @Schema(description = "기업명", example="카카오")
    private String company;
    @Schema(description = "직무", example="글로벌마케팅")
    private String job;
    @Schema(description = "질문카드의 상황 키워드", example="[\"자유로운\",\"다대일\",\"긍정적\"]")
    private List<Status> status;
    @Schema(description = "질문 카드의 답변 전략", example="공손하게 회피")
    private String strategy;
    @Schema(description = "질문 카드의 답변 내용", example="동기마다 강점이 달라 조심스럽습니다. 저는 성장하는 자세가 강점입니다.")
    private String answerContent;
    @Schema(description = "질문 카드의 생성 날짜", example="2025-05-28T18:30:00")
    private LocalDateTime createdAt;
    @Schema(description = "유저 ID", example="1")
    private Long userId;

    @Schema(description = "답변의 전략")
    private List<CSARDtosStrategyDto> strategies;
    @Schema(description = "답변")
    private List<CSARDtosAnswerDto> answers;

    public CardStrategiesAnswersResponseDto(Card card, List<CSARDtosStrategyDto> strategies, List<CSARDtosAnswerDto> answers) {
        this.cardId = card.getId();
        this.questionContent = card.getQuestionContent();
        this.company = card.getCompanyName();
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

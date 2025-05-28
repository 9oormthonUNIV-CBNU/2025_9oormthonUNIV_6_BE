package com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers;

import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CSARDtosAnswerDto {
    private BigInteger answerId;
    private String content;
    private LocalDateTime createdAt;
    private BigInteger likes;
    private Long userId;
    private String userNickname;

    public static CSARDtosAnswerDto create(Answer answer) {
        return new CSARDtosAnswerDto(
                answer.getId(),
                answer.getContent(),
                answer.getCreatedAt(),
                answer.getLikes(),
                answer.getUser().getId(),
                answer.getUser().getNickname()
        );
    }
}

package com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers;

import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CSARDtosAnswerDto {
    @Schema(description = "답변 ID", example="1")
    private Long answerId;
    @Schema(description = "답변 내용", example="저는 계획적으로 일하고 책임감이 강한 편입니다.")
    private String content;
    @Schema(description = "생성 날짜", example="2025-05-28T18:30:00")
    private LocalDateTime createdAt;
    @Schema(description = "좋아요 수", example="1")
    private int likes;
    @Schema(description = "유저 ID", example="1")
    private Long userId;
    @Schema(description = "유저 이름", example="러키")
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

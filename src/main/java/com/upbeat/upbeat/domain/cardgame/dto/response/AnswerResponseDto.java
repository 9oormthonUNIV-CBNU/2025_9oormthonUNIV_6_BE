package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AnswerResponseDto {
//    private Long id;
//    private String content;
//    private LocalDateTime createdAt;
//    private Long likes;
//    private Long userId;
//    private Long cardId;
//    private Long strategyId;
//    private List<Like> likesList;
    @Schema(description = "메세지", example="답변이 등록되었습니다.")
    private String message;

    public static AnswerResponseDto createAnswerDto(Answer answer) {
        return new AnswerResponseDto(
//                answer.getId(),
//                answer.getContent(),
//                answer.getCreatedAt(),
//                answer.getLikes(),
//                answer.getUser().getId(),
//                answer.getCard().getId(),
//                answer.getStrategy().getId(),
//                answer.getLikesList()
                "답변이 등록되었습니다."
        );
    }
}

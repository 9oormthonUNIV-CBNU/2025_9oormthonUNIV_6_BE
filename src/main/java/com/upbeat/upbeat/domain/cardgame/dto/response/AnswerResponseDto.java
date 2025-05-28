package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class AnswerResponseDto {
//    private BigInteger id;
//    private String content;
//    private LocalDateTime createdAt;
//    private BigInteger likes;
//    private Long userId;
//    private BigInteger cardId;
//    private BigInteger strategyId;
//    private List<Like> likesList;
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
                "전략이 등록되었습니다."
        );
    }
}

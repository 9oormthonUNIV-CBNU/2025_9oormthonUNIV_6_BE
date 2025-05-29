package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class LikeResponseDto {
//    private Long id;
//    private User user;
//    private Answer answer;
    @Schema(description = "메세지", example="좋아요가 반영되었습니다.")
    private String message;
    @Schema(description = "좋아요 수", example="1")
    private int likes;

    public static LikeResponseDto createLikeDto(Like like, int likeCount) {
        return new LikeResponseDto(
                "좋아요가 반영되었습니다.",
                likeCount
        );
    }
}

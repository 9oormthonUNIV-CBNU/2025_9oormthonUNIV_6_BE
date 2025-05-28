package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class LikeResponseDto {
//    private BigInteger id;
//    private User user;
//    private Answer answer;
    private String message;
    private BigInteger likes;

    public static LikeResponseDto createLikeDto(Like like, BigInteger likeCount) {
        return new LikeResponseDto(
                "좋아요가 반영되었습니다.",
                likeCount
        );
    }
}

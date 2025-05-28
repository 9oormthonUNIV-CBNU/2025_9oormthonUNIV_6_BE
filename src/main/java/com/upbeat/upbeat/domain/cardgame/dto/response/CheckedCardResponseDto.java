package com.upbeat.upbeat.domain.cardgame.dto.response;

import com.upbeat.upbeat.domain.cardgame.entity.CheckedCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CheckedCardResponseDto {
//    private BigInteger id;
//    private BigInteger cardId;
//    private Long userId;
    private String message;

    //이 함수는 DTO를 생성하고 그 반환값을 사용하지 않기 때문에(API를 보내지 않음) 의미가 없음.
    public static CheckedCardResponseDto createCheckedCardDto(CheckedCard card) {
        return new CheckedCardResponseDto(
//                card.getId(),
//                card.getCard().getId(),
//                card.getUser().getId(),
                "카드 열람 기록 저장"
        );
    }
}

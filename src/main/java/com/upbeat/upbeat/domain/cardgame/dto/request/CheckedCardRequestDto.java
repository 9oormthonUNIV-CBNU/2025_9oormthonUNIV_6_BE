package com.upbeat.upbeat.domain.cardgame.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CheckedCardRequestDto {
    //CheckedCardRequestDto는 POST가 아닌 GET으로 요청하기 때문에 DTO가 필요 없음.
    //하지만 userId를 Controller에서 받을 방법을 찾아야 함.
    private BigInteger cardId;
    private Long userId;
}

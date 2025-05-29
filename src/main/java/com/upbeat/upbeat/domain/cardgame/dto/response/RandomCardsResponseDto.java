package com.upbeat.upbeat.domain.cardgame.dto.response;

import lombok.*;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RandomCardsResponseDto {
    private List<Long> cardId;
}

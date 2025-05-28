package com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds;

import com.upbeat.upbeat.domain.cardgame.entity.CheckedCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CACRDtosCheckedCardDto {
    private BigInteger checkedCardId;
    private BigInteger cardId;
    private Long userId;

    public static CACRDtosCheckedCardDto create(CheckedCard card) {
        return new CACRDtosCheckedCardDto(
                card.getId(),
                card.getCard().getId(),
                card.getUser().getId()
        );
    }
}

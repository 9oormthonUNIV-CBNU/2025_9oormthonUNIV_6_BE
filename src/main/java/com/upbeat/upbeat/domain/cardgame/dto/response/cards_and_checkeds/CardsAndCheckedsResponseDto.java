package com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CardsAndCheckedsResponseDto {
    private List<CACRDtosCardDto> cards;
    private List<CACRDtosCheckedCardDto> checkedCards;
}

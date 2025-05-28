package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.CardRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.CardResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CSARDtosAnswerDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CSARDtosStrategyDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CardStrategiesAnswersResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds.CACRDtosCardDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds.CACRDtosCheckedCardDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds.CardsAndCheckedsResponseDto;
import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.service.AnswerService;
import com.upbeat.upbeat.domain.cardgame.service.CardService;
import com.upbeat.upbeat.domain.cardgame.service.CheckedCardService;
import com.upbeat.upbeat.domain.cardgame.service.StrategyService;
import com.upbeat.upbeat.global.security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@Slf4j
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private CheckedCardService checkedCardService;
    @Autowired
    private StrategyService strategyService;

    //카드 생성
    @PostMapping("/api/cards")
    public ResponseEntity<CardResponseDto> CreateCard(@RequestBody CardRequestDto dto, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        CardResponseDto card = cardService.create(dto,userId);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    //6개 랜덤 카드
    @GetMapping("/api/cards/show")
    public ResponseEntity<CardsAndCheckedsResponseDto> GetRandomCards(@AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        List<CACRDtosCardDto> cards = cardService.showRandom();
        List<CACRDtosCheckedCardDto> checkedCards = checkedCardService.response(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new CardsAndCheckedsResponseDto(cards,checkedCards));
    }

    //카드&답변 보기
    //(UserId 받아오는 방법 수정)
    @GetMapping("/api/cards/{cardId}/show/{userId}")
    public ResponseEntity<CardStrategiesAnswersResponseDto> GetCardWithAnswers(@PathVariable("cardId") BigInteger cardId, @PathVariable Long userId){

        Card card = cardService.show(cardId);
        if (card == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        List<CSARDtosStrategyDto> strategies = strategyService.show(cardId);
        List<CSARDtosAnswerDto> answers = answerService.show(cardId);
        if (checkedCardService.isExist(userId, cardId) == null) {
            checkedCardService.create(userId, cardId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CardStrategiesAnswersResponseDto(card, strategies,answers));
    }
}
package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.CardRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.CardResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CSARDtosAnswerDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CSARDtosStrategyDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CardStrategiesAnswersResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.RandomCardsResponseDto;
import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.service.AnswerService;
import com.upbeat.upbeat.domain.cardgame.service.CardService;
import com.upbeat.upbeat.domain.cardgame.service.StrategyService;
import com.upbeat.upbeat.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Tag(name = "card-controller")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/cards")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private StrategyService strategyService;

    //카드 생성
    @Operation(summary = "카드 생성")
    @PostMapping("")
    public ResponseEntity<CardResponseDto> CreateCard(@Valid @RequestBody CardRequestDto dto, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        CardResponseDto card = cardService.create(dto,userId);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    //6개 랜덤 카드
    @Operation(summary = "6개 랜덤 카드 추출")
    @GetMapping("/cardinfo")
    public ResponseEntity<RandomCardsResponseDto> GetRandomCards(){
        List<Long> cards = cardService.showRandom();
        return ResponseEntity.status(HttpStatus.OK).body(new RandomCardsResponseDto(cards));
    }

    //카드&답변 보기
    @Operation(summary = "카드&답변 열람")
    @GetMapping("/{cardId}/show")
    public ResponseEntity<CardStrategiesAnswersResponseDto> GetCardWithAnswers(@PathVariable("cardId") Long cardId){
        Card card = cardService.show(cardId);
        if (card == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        List<CSARDtosStrategyDto> strategies = strategyService.show(cardId);
        List<CSARDtosAnswerDto> answers = answerService.show(cardId);

        return ResponseEntity.status(HttpStatus.OK).body(new CardStrategiesAnswersResponseDto(card, strategies,answers));
    }
}
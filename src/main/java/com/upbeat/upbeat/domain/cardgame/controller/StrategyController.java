package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.StrategyRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.StrategyResponseDto;
import com.upbeat.upbeat.domain.cardgame.service.StrategyService;
import com.upbeat.upbeat.global.security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@Slf4j
public class StrategyController {
    @Autowired
    private StrategyService strategyService;

    //전략 생성
    @PostMapping("/api/cards/{cardId}/strategy")
    public ResponseEntity<StrategyResponseDto> CreateStrategy(@PathVariable BigInteger cardId, @RequestBody StrategyRequestDto strategyDTO,@AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        StrategyResponseDto created = strategyService.create(cardId,strategyDTO,userId);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
}

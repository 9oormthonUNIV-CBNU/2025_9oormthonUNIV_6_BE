package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.StrategyRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.StrategyResponseDto;
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

@Tag(name = "answer-strategy-controller")
@RestController
@Slf4j
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class StrategyController {
    @Autowired
    private StrategyService strategyService;

    //전략 생성
    @Operation(summary = "전략 생성")
    @PostMapping("/{cardId}/strategy")
    public ResponseEntity<StrategyResponseDto> CreateStrategy(@PathVariable Long cardId, @Valid @RequestBody StrategyRequestDto strategyDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        StrategyResponseDto created = strategyService.create(cardId,strategyDTO,userId);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
}

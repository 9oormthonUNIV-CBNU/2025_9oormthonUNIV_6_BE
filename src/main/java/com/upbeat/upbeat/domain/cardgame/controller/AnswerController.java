package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.AnswerRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.AnswerResponseDto;
import com.upbeat.upbeat.domain.cardgame.service.AnswerService;
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

@Tag(name = "card-answer-controller")
@RestController
@Slf4j
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    //답변 생성
    @Operation(summary = "답변 생성")
    @PostMapping("/{cardId}/answer")
    public ResponseEntity<AnswerResponseDto> CreateAnswer(@PathVariable Long cardId, @Valid @RequestBody AnswerRequestDto answerDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        AnswerResponseDto created = answerService.create(cardId,answerDTO,userId);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
}

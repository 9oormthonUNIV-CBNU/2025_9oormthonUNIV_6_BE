package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.AnswerRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.AnswerResponseDto;
import com.upbeat.upbeat.domain.cardgame.service.AnswerService;
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
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    //답변 생성
    @PostMapping("/api/cards/{cardId}/answer") //url 변경
    public ResponseEntity<AnswerResponseDto> CreateAnswer(@PathVariable BigInteger cardId, @RequestBody AnswerRequestDto answerDTO,@AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        AnswerResponseDto created = answerService.create(cardId,answerDTO,userId);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
}

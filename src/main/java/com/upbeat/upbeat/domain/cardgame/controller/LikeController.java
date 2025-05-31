package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.LikeRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.LikeResponseDto;
import com.upbeat.upbeat.domain.cardgame.service.LikeService;
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

@Tag(name = "answer-like-controller")
@RestController
@Slf4j
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class LikeController {
    @Autowired
    private LikeService likeService;

    //좋아요 증가
    @Operation(summary = "좋아요 증가")
    @PostMapping("/{cardId}/like")
    public ResponseEntity<LikeResponseDto> increaseLike(@Valid @RequestBody LikeRequestDto dto, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        LikeResponseDto responseDto = likeService.create(dto, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //좋아요 취소
    @Operation(summary = "좋아요 취소")
    @DeleteMapping("/{cardId}/likeCancel/{answerId}")
    public ResponseEntity<LikeResponseDto> cancelLike(@PathVariable Long cardId, @PathVariable Long answerId, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        LikeResponseDto responseDto = likeService.delete(answerId,userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

package com.upbeat.upbeat.domain.cardgame.controller;

import com.upbeat.upbeat.domain.cardgame.dto.request.LikeRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.LikeResponseDto;
import com.upbeat.upbeat.domain.cardgame.service.LikeService;
import com.upbeat.upbeat.global.security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@Slf4j
public class LikeController {
    @Autowired
    private LikeService likeService;

    //좋아요 증가
    @PostMapping("/api/cards/{cardId}/like")
    public ResponseEntity<LikeResponseDto> IncreaseLikes(@RequestBody LikeRequestDto dto){
        LikeResponseDto responseDto = likeService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //좋아요 취소
    //(AnswerId, UserId 받아오는 방법 수정)
    @DeleteMapping("/api/cards/{cardId}/likeCancel/{answerId}")
    public ResponseEntity<LikeResponseDto> 함수명(@PathVariable BigInteger cardId, @PathVariable BigInteger answerId, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        LikeResponseDto responseDto = likeService.delete(answerId,userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

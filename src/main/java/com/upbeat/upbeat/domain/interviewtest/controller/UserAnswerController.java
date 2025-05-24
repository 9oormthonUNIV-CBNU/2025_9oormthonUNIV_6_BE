package com.upbeat.upbeat.domain.interviewtest.controller;

import com.upbeat.upbeat.domain.interviewtest.dto.ResultTypeResponseDto;
import com.upbeat.upbeat.domain.interviewtest.dto.UserAnswerRequestDto;
import com.upbeat.upbeat.domain.interviewtest.dto.UserAnswerResponseDto;
import com.upbeat.upbeat.domain.interviewtest.entity.UserAnswer;
import com.upbeat.upbeat.domain.interviewtest.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-answers")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    //사용자 응답 저장 api
    @PostMapping
    public void submitAnswer(@RequestBody UserAnswerRequestDto dto) {
        userAnswerService.saveUserAnswer(dto);
    }

    //사용자 응답 전체조회 api
    @GetMapping("/{userId}")
    public List<UserAnswerResponseDto> getUserAnswers(@PathVariable Long userId) {
        return userAnswerService.getUserAnswerDtos(userId);
    }
    // 사용자 응답 기반 결과 유형 반환 API
    @GetMapping("/result/{userId}")
    public ResultTypeResponseDto getUserResult(@PathVariable Long userId) {
        return userAnswerService.getUserResultType(userId);
    }
    //type유형 반환으로 인해 새로 추가한 url
    @GetMapping("/result/redirect/{userId}")
    public Map<String, String> getRedirectUrl(@PathVariable Long userId) {
        return userAnswerService.getRedirectUrlByUserResult(userId);
    }

}

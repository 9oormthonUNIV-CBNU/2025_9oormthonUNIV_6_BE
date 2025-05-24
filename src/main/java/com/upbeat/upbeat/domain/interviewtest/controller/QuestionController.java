package com.upbeat.upbeat.domain.interviewtest.controller;

import com.upbeat.upbeat.domain.interviewtest.dto.QuestionResponseDto;
import com.upbeat.upbeat.domain.interviewtest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;//서비스 계층과 연결되는 필드

    @GetMapping//전체 질문을 조회합니다.
    public List<QuestionResponseDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @GetMapping("/{id}")//특정 질문 id로 질문 상세조회합니다. 질문 id는 1~10입니다.
    public QuestionResponseDto getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }
}

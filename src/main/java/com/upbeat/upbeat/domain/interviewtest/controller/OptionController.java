package com.upbeat.upbeat.domain.interviewtest.controller;

import com.upbeat.upbeat.domain.interviewtest.dto.OptionResponseDto;
import com.upbeat.upbeat.domain.interviewtest.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//api 서버 역할을 한다. 반환값을 view로 랜더링하지 않고 json형태로 응답하게 해준다.
@RequiredArgsConstructor//final 필드나 @nonnull필드를 생성자 파라미터로 갖는 생성자를 자동 생성해준다.
@RequestMapping("/api/options")
public class OptionController {
    private final OptionService optionService;//서비스 계층과 연결되는 필드

    //특정 질문 id에 대한 보기 목록을 반환합니다. ex) api/options/question/1을 요청하면 질문에 해당하는 보기 2개가 나옵니다.
    @GetMapping("/question/{questionId}")//옵션에 해당하는 질문
    public List<OptionResponseDto> getOptionByQuestionId(@PathVariable Long questionId) {//questionid를 기준으로 옵션들을 조회해서 list형태로 반환한다.
        return optionService.getOptionsByQuestionId(questionId);
    }
}

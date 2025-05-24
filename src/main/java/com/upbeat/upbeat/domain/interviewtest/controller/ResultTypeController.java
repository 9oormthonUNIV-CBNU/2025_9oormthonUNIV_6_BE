package com.upbeat.upbeat.domain.interviewtest.controller;

import com.upbeat.upbeat.domain.interviewtest.dto.ResultTypeResponseDto;
import com.upbeat.upbeat.domain.interviewtest.service.ResultTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class ResultTypeController {
    private final ResultTypeService resultTypeService;

    @GetMapping("/{code}")//프론트에서 모든 결과유형 보기 기능시 필요할 수도 있어서 만들어놨음
    public ResultTypeResponseDto getResult(@PathVariable String code) {
        return resultTypeService.getResultByTypeCode(code);
    }
}

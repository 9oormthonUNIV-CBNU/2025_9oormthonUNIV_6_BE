package com.upbeat.upbeat.domain.interviewtest.service;

import com.upbeat.upbeat.domain.interviewtest.dto.ResultTypeResponseDto;
import com.upbeat.upbeat.domain.interviewtest.entity.ResultType;
import com.upbeat.upbeat.domain.interviewtest.repository.ResultTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultTypeService {

    private final ResultTypeRepository resultTypeRepository;

    public ResultTypeResponseDto getResultByTypeCode(String code) {
        ResultType resultType = resultTypeRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("해당 결과 유형이 없습니다: code = " + code));
        return new ResultTypeResponseDto(resultType.getName(), resultType.getDescription());
    }
}

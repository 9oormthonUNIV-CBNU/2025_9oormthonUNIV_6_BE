package com.upbeat.upbeat.domain.interviewtest.service;

import com.upbeat.upbeat.domain.interviewtest.dto.OptionResponseDto;
import com.upbeat.upbeat.domain.interviewtest.entity.Option;
import com.upbeat.upbeat.domain.interviewtest.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final 필드 기반 생성자 자동 생성
public class OptionService {

    private final OptionRepository optionRepository;

    public List<OptionResponseDto> getOptionsByQuestionId(Long questionId) {
        List<Option> options = optionRepository.findByQuestionId(questionId);

        return options.stream()
                .map(opt -> new OptionResponseDto(
                        opt.getId(),
                        opt.getLabel(),
                        opt.getContent(),
                        opt.getTypeCode()
                ))
                .toList();
    }
}

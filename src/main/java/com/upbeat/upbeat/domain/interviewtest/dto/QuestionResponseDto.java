//테스트 결과를 나타냄
package com.upbeat.upbeat.domain.interviewtest.dto;

import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionResponseDto {
    private Long id;
    private String question;
    private List<OptionResponseDto> options;

    public static QuestionResponseDto from(Question question) {
        List<OptionResponseDto> options = question.getOptions().stream()
                .map(opt -> new OptionResponseDto(opt.getId(), opt.getLabel(), opt.getContent()))
                .toList();

        return new QuestionResponseDto(question.getId(), question.getQuestion(), options);
    }
}

package com.upbeat.upbeat.domain.interviewtest.dto;

import com.upbeat.upbeat.domain.interviewtest.entity.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAnswerResponseDto {
    private Long id;
    private Long userId;
    private Long questionId;
    private String questionContent;
    private Long optionId;
    private String optionLabel;
    private String optionContent;

    public static UserAnswerResponseDto from(UserAnswer ua) {
        return new UserAnswerResponseDto(
                ua.getId(),
                ua.getUser().getId(),
                ua.getQuestion().getId(),
                ua.getQuestion().getQuestion(),
                ua.getOption().getId(),
                ua.getOption().getLabel(),
                ua.getOption().getContent()
        );
    }
}

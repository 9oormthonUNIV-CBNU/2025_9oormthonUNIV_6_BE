//특정 질문에 대해 선택한 답변을 백엔드로 전달할 때 사용
package com.upbeat.upbeat.domain.interviewtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAnswerRequestDto {
    //private Long userId;
    private Long questionId;
    private Long optionId;
}

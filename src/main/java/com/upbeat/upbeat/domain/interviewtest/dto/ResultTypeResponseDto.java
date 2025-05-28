//특정 질문에 대해 선택한 답변을 백엔드로 전달할 때 사용
package com.upbeat.upbeat.domain.interviewtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultTypeResponseDto {
    private String name;
    private String description;
}

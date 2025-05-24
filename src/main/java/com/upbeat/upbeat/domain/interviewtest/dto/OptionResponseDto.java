//질문에 대한 보기(옵션) 정보를 클라이언트에 전달하기 위함
package com.upbeat.upbeat.domain.interviewtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionResponseDto {
    private Long id;
    private String label;
    private String content;
    private String typeCode;
}

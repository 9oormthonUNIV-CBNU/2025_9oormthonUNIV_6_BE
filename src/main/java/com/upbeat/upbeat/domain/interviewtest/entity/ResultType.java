//결과유형반환
package com.upbeat.upbeat.domain.interviewtest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="result_types")
@Getter
@NoArgsConstructor
public class ResultType {
    @Id
    private String code;// Type1, Type2등등 분류하는 코드

    private String name;//두루뭉술 낭만가형

    private String description;//유형 설명
}

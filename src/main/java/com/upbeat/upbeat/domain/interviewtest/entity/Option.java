//응답
package com.upbeat.upbeat.domain.interviewtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="options")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String label; // 'A' 혹은 'B' 구분

    private String content; // 보기 텍스트

    private String typeCode; // 유형 분류용 태그

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
}

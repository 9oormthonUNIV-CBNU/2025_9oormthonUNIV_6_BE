//질문
package com.upbeat.upbeat.domain.interviewtest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_question")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)//Option엔티티에서 관계의 주인이 question이다. cascade로 인해 Question삭제시 관련 option도 삭제 된다.
    private List<Option> options = new ArrayList<>();
}

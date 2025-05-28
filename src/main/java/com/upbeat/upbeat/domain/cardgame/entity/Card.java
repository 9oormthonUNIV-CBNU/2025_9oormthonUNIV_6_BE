package com.upbeat.upbeat.domain.cardgame.entity;

import com.upbeat.upbeat.domain.cardgame.dto.request.CardRequestDto;
import com.upbeat.upbeat.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor //기본 생성자
@ToString
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id", nullable = false)
    private BigInteger id;

    @Column(nullable = false,length=30)
    private String questionContent; //질문내용
    @Column(nullable = false,length=30)
    private String company; //기업명
    @Column(nullable = false,length=30)
    private String job; //직무
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "card_status_keywords", joinColumns = @JoinColumn(name = "card_id"))
    @Column(nullable = false)
    private List<Status> statusKeyword; //상황키워드

    @Enumerated(EnumType.STRING)
    private AnswerStrategy strategy; //전략
    @Column(length=30)
    private String answerContent; //답변 내용
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt; //생성일시

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user; //회원ID

    @OneToMany(mappedBy = "card")
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "card")
    private List<CheckedCard> checkedCardList = new ArrayList<>();

    public static Card createCard(CardRequestDto dto, User user) {
        String strategy = dto.getStrategy();
        AnswerStrategy enumStrategy = AnswerStrategy.fromDescription(strategy);

        return new Card(
                null,
                dto.getQuestionContent(),
                dto.getCompanyName(),
                dto.getJob(),
                dto.getStatus(),
                enumStrategy,
                dto.getAnswerContent(),
                null,
                user,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}

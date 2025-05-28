package com.upbeat.upbeat.domain.cardgame.entity;

import com.upbeat.upbeat.domain.cardgame.dto.request.AnswerRequestDto;
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
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private BigInteger id;
    @Column(nullable = false,length=30)
    private String content; //내용
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; //생성일시
    private BigInteger likes; //좋아요

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user; //회원ID
    @ManyToOne
    @JoinColumn(name="card_id",nullable = false)
    private Card card; //카드ID

    @OneToOne
    @JoinColumn(name="strategy_id",nullable = false)
    private Strategy strategy;

    @OneToMany(mappedBy = "answer")
    private List<Like> likesList= new ArrayList<>();

    public static Answer createAnswer(AnswerRequestDto dto, Card card, User user, Strategy strategy) {
        if(dto.getCardId() != card.getId()) throw new IllegalArgumentException("CardAnswer cardId must be the same");

        return new Answer(
                null,
                dto.getContent(),
                null,
                BigInteger.ZERO,
                user,
                card,
                strategy,
                new ArrayList<>()
        );
    }
}

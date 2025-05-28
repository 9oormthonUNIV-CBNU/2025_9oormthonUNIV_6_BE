package com.upbeat.upbeat.domain.cardgame.entity;

import com.upbeat.upbeat.domain.cardgame.dto.request.StrategyRequestDto;
import com.upbeat.upbeat.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Strategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="strategy_id")
    private BigInteger id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerStrategy strategy; //전략

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user; //회원ID
    @ManyToOne
    @JoinColumn(name="card_id",nullable = false)
    private Card card; //카드ID

    @OneToOne(mappedBy = "strategy")
    private Answer answer;

    public static Strategy createStrategy(StrategyRequestDto dto, Card card, User user) {
        if(dto.getCardId() != card.getId()) throw new IllegalArgumentException("CardAnswer cardId must be the same");

        String strategy = dto.getStrategy();
        AnswerStrategy enumStrategy = AnswerStrategy.fromDescription(strategy);

        return new Strategy(
                null,
                enumStrategy,
                user,
                card,
                null
        );
    }
}

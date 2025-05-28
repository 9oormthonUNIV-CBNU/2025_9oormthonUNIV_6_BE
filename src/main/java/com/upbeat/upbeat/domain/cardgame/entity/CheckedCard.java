package com.upbeat.upbeat.domain.cardgame.entity;

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
public class CheckedCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="checked_card_id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name="card_id",nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;



    public static CheckedCard createCheckedCard(Card card, User user) {
        if(card.getId() == null) throw new IllegalArgumentException("CardId is null");

        return new CheckedCard(
                null,
                card,
                user
        );
    }
}

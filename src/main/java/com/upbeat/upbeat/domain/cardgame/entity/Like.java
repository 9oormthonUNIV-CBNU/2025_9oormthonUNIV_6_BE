package com.upbeat.upbeat.domain.cardgame.entity;

import com.upbeat.upbeat.domain.cardgame.dto.request.LikeRequestDto;
import com.upbeat.upbeat.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor //기본 생성자
@ToString
@Entity
@Getter
@Setter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="likes_id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name="answer_id",nullable = false)
    private Answer answer;

    public static Like create(LikeRequestDto dto, User user,Answer answer) {
        if(dto.getAnswerId() != answer.getId()) throw new IllegalArgumentException("Answer Id must be the same");

        return new Like(
                null,
                user,
                answer
        );
    }
}

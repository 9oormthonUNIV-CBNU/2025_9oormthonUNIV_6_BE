package com.upbeat.upbeat.domain.cardgame.repository;

import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import com.upbeat.upbeat.domain.cardgame.entity.Like;
import com.upbeat.upbeat.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Override
    ArrayList<Like> findAll();

    int countByAnswerId(Long answerId);

    @Query(value = "SELECT * FROM likes WHERE answer_id = :answerId AND user_id = :userId", nativeQuery = true)
    Optional<Like> findByAnswerIdAndUserId(Long answerId, Long userId);

    boolean existsByUserIdAndAnswerId(Long userId, Long answerId);
}

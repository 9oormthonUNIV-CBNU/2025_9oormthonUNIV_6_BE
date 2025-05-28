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

public interface LikeRepository extends JpaRepository<Like, BigInteger> {
    @Override
    ArrayList<Like> findAll();

    BigInteger countByAnswerId(BigInteger answerId);

    List<Like> user(User user);

    BigInteger answer(Answer answer);

    @Query(value = "SELECT * FROM like WHERE answer_id = :answerId AND user_id = :userId", nativeQuery = true)
    Optional<Like> findByAnwerIdAndUserId(BigInteger answerId, Long userId);
}

package com.upbeat.upbeat.domain.cardgame.repository;

import com.upbeat.upbeat.domain.cardgame.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface StrategyRepository extends JpaRepository<Strategy, Long> {
    @Query(value = "SELECT * FROM strategies WHERE card_id = :id", nativeQuery = true)
    List<Strategy> findByCardId(Long id);

    @Query(value = "SELECT * FROM strategies WHERE user_id = :userId AND card_id = :cardId", nativeQuery = true)
    Optional<Strategy> findByUserIdAndCardId(Long userId, Long cardId);
}

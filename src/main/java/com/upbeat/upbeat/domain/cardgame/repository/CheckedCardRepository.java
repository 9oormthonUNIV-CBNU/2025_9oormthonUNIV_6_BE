package com.upbeat.upbeat.domain.cardgame.repository;

import com.upbeat.upbeat.domain.cardgame.entity.CheckedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CheckedCardRepository extends JpaRepository<CheckedCard, BigInteger> {
    @Query(value = "SELECT * FROM checked_card WHERE user_id = :id", nativeQuery = true)
    List<CheckedCard> findByUserId(Long id);

    @Query(value = "SELECT * FROM checked_card WHERE user_id = :userId AND card_id = :cardId", nativeQuery = true)
    Optional<CheckedCard> findByUserIdAndCardId(Long userId, BigInteger cardId);
}

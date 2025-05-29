package com.upbeat.upbeat.domain.cardgame.repository;

import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT * FROM answers WHERE card_id = :id", nativeQuery = true)
    List<Answer> findByCardId(Long id);
}
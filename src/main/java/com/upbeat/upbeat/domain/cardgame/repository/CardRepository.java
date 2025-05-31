package com.upbeat.upbeat.domain.cardgame.repository;

import com.upbeat.upbeat.domain.cardgame.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Override
    ArrayList<Card> findAll(); //모든 데이터 찾기 위한 문장

    @Query(value = "SELECT card_id FROM cards ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Long> findRandomCards();
}
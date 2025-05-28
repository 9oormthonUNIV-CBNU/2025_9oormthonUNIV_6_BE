package com.upbeat.upbeat.domain.interviewtest.repository;

import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT DISTINCT q FROM Question q JOIN FETCH q.options")
    List<Question> findAllDistinct(); // 전체 조회용: 옵션까지 fetch

    @Query("SELECT q FROM Question q JOIN FETCH q.options WHERE q.id = :id")
    Optional<Question> findByIdWithOptions(Long id); // 단일 조회용: 옵션까지 fetch
}

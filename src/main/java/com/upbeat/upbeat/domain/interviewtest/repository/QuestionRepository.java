package com.upbeat.upbeat.domain.interviewtest.repository;

import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT DISTINCT q FROM Question q JOIN FETCH q.options")
    List<Question> findAllDistinct(); // 중복 제거 + 연관 객체 Fetch
}

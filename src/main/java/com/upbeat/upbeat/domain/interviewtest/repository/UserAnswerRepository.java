package com.upbeat.upbeat.domain.interviewtest.repository;

import com.upbeat.upbeat.domain.interviewtest.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserId(Long userId);
    boolean existsByUserIdAndQuestionId(Long userId, Long questionId);
}

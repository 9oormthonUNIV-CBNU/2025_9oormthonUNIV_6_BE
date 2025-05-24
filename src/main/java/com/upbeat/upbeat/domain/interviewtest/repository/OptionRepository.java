package com.upbeat.upbeat.domain.interviewtest.repository;

import com.upbeat.upbeat.domain.interviewtest.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestionId(Long questionId);
}

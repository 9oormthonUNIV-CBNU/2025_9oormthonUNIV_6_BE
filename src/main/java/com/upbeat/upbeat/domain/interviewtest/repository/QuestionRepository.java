package com.upbeat.upbeat.domain.interviewtest.repository;

import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}//JpaRepository<T, ID>에서 T는 관리할 엔티티 클래스의 타입, ID는 그 엔티티의 PK타입이다.

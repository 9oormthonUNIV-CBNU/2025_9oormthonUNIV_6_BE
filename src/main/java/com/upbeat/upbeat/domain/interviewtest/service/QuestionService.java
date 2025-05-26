package com.upbeat.upbeat.domain.interviewtest.service;

import com.upbeat.upbeat.domain.interviewtest.dto.QuestionResponseDto;
import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import com.upbeat.upbeat.domain.interviewtest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponseDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAllDistinct();
        return questions.stream()
                .map(QuestionResponseDto::from)
                .toList();
    }

    public QuestionResponseDto getQuestionById(Long id) {
        Question question = questionRepository.findByIdWithOptions(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 존재하지 않습니다: id = " + id));
        return QuestionResponseDto.from(question);
    }
}

package com.upbeat.upbeat.domain.interviewtest.service;

import com.upbeat.upbeat.domain.interviewtest.dto.QuestionResponseDto;
import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import com.upbeat.upbeat.domain.interviewtest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponseDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAllDistinct();  // 수정된 부분
        return questions.stream()
                .map(QuestionResponseDto::from)
                .toList();
    }
    /*
    stream을 통해 변환처리를 위한 흐름 생성 -> map을 통해 각 질문을 DTO로 변환 ->collect를 통해 변환된 DTO들을 리스트로 묶음
    ->List<QuestionResponseDto> 반환
    */
    public QuestionResponseDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 질문이 존재하지 않습니다: id = " +id));

        return QuestionResponseDto.from(question);
    }//특정 질문 조회를 위한 api(추가 기능)
}

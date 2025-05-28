package com.upbeat.upbeat.domain.cardgame.service;

import com.upbeat.upbeat.domain.cardgame.dto.request.AnswerRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.AnswerResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CSARDtosAnswerDto;
import com.upbeat.upbeat.domain.cardgame.entity.Answer;
import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.entity.Strategy;
import com.upbeat.upbeat.domain.cardgame.repository.AnswerRepository;
import com.upbeat.upbeat.domain.cardgame.repository.CardRepository;
import com.upbeat.upbeat.domain.cardgame.repository.StrategyRepository;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StrategyRepository strategyRepository;

    public List<CSARDtosAnswerDto> show(BigInteger id){
        return answerRepository.findByCardId(id)
                .stream()
                .map(answer -> CSARDtosAnswerDto.create(answer))
                .collect(Collectors.toList());
    }

    @Transactional
    public AnswerResponseDto create(BigInteger cardId, AnswerRequestDto dto,Long userId){
        Card card = cardRepository.findById(cardId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패."+"대상 게시글이 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        Strategy strategy = strategyRepository.findByUserIdAndCardId(userId, cardId).orElse(null);
        Answer answer = Answer.createAnswer(dto,card,user,strategy);
        Answer saved = answerRepository.save(answer);

        return AnswerResponseDto.createAnswerDto(saved);
    }
}

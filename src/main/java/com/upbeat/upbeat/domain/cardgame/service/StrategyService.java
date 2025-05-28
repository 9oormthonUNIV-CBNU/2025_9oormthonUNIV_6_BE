package com.upbeat.upbeat.domain.cardgame.service;

import com.upbeat.upbeat.domain.cardgame.dto.request.StrategyRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.StrategyResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.card_strategies_answers.CSARDtosStrategyDto;
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
public class StrategyService {
    @Autowired
    private StrategyRepository repository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public List<CSARDtosStrategyDto> show(BigInteger id){
        return repository.findByCardId(id)
                .stream()
                .map(strategy -> CSARDtosStrategyDto.create(strategy))
                .collect(Collectors.toList());
    }

    @Transactional
    public StrategyResponseDto create(BigInteger cardId, StrategyRequestDto dto,Long userId){
        Card card = cardRepository.findById(cardId).orElseThrow(()->new IllegalArgumentException("전략 생성 실패."+"대상 게시글이 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        Strategy strategy = Strategy.createStrategy(dto,card,user);
        Strategy saved = repository.save(strategy);

        return StrategyResponseDto.createStrategyDto(saved);
    }
}

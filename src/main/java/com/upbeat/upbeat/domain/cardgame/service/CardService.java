package com.upbeat.upbeat.domain.cardgame.service;

import com.upbeat.upbeat.domain.cardgame.dto.request.CardRequestDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.CardResponseDto;
import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.repository.CardRepository;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    @Autowired
    private CardRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<Long> showRandom(){
        return repository.findRandomCards();
    }

    public Card show(Long id){
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public CardResponseDto create(CardRequestDto dto,Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        Card card = Card.createCard(dto,user);
        Card saved = repository.save(card);

        return CardResponseDto.createCardDto(saved);
    }
}

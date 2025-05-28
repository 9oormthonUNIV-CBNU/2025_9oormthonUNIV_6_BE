package com.upbeat.upbeat.domain.cardgame.service;

import com.upbeat.upbeat.domain.cardgame.dto.response.CheckedCardResponseDto;
import com.upbeat.upbeat.domain.cardgame.dto.response.cards_and_checkeds.CACRDtosCheckedCardDto;
import com.upbeat.upbeat.domain.cardgame.entity.Card;
import com.upbeat.upbeat.domain.cardgame.entity.CheckedCard;
import com.upbeat.upbeat.domain.cardgame.repository.CardRepository;
import com.upbeat.upbeat.domain.cardgame.repository.CheckedCardRepository;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckedCardService {
    @Autowired
    private CheckedCardRepository checkedCardRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CheckedCardResponseDto create(Long userId, BigInteger cardId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        Card card = cardRepository.findById(cardId).orElse(null);

        CheckedCard checkedCard = CheckedCard.createCheckedCard(card,user);
        CheckedCard saved = checkedCardRepository.save(checkedCard);

        return CheckedCardResponseDto.createCheckedCardDto(saved);
    }

    public List<CACRDtosCheckedCardDto> response(Long userId){
        return checkedCardRepository.findByUserId(userId)
                .stream()
                .map(card -> CACRDtosCheckedCardDto.create(card))
                .collect(Collectors.toList());
    }

    public CheckedCard isExist(Long userId, BigInteger cardId){
        return checkedCardRepository.findByUserIdAndCardId(userId,cardId).orElse(null);
    }
}

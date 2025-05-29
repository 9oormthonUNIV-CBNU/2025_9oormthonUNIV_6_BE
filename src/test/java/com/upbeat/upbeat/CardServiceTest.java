package com.upbeat.upbeat;

import com.upbeat.upbeat.domain.cardgame.dto.request.*;
import com.upbeat.upbeat.domain.cardgame.dto.response.*;
import com.upbeat.upbeat.domain.cardgame.entity.*;
import com.upbeat.upbeat.domain.cardgame.repository.*;
import com.upbeat.upbeat.domain.cardgame.service.AnswerService;
import com.upbeat.upbeat.domain.cardgame.service.CardService;
import com.upbeat.upbeat.domain.cardgame.service.LikeService;
import com.upbeat.upbeat.domain.cardgame.service.StrategyService;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Getter
@SpringBootTest
@Transactional
public class CardServiceTest {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private CardService cardService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private StrategyService strategyService;

    private User user;

//    @BeforeEach
//    void setUp() {
//        user = new User(1L,"abcd1234","abcd1234","청주","충북대","010-1234-1234","abcd1234@gmail.com");
//        userRepository.save(user);
//    }
//
//    @Test
//    void 카드_생성() {
//        CardRequestDto dto = new CardRequestDto();
//        dto.setQuestionContent("장점을 말해주세요");
//        dto.setCompanyName("카카오");
//        dto.setJob("백엔드");
//        dto.setStatus(List.of(Status.긍정적, Status.다대일));
//        dto.setStrategy("공손하게 회피");
//        dto.setAnswerContent("성실함이 장점입니다");
//
//        CardResponseDto response = cardService.create(dto, user.getId());
//
//        assertThat(response.getMessage()).isEqualTo("질문 등록 완료");
//    }
//
//    @Test
//    void 답변_생성() {
//        CardRequestDto dto = new CardRequestDto();
//        dto.setQuestionContent("단점을 말해주세요");
//        dto.setCompanyName("네이버");
//        dto.setJob("프론트엔드");
//        dto.setStatus(List.of(Status.부정적));
//        dto.setStrategy("질문 재확인 후 답변");
//        dto.setAnswerContent("완벽주의입니다");
//
//        CardResponseDto card = cardService.create(dto, user.getId());
//
//        AnswerRequestDto answerDto = new AnswerRequestDto();
//        answerDto.setContent("완벽주의가 단점이지만 장점이기도 합니다");
//
//        AnswerResponseDto answer = answerService.create(card.getCardId(), answerDto, user.getId());
//
//        assertThat(answer.getMessage()).isEqualTo("답변이 등록되었습니다.");
//    }
//
//    @Test
//    void 전략_생성() {
//        CardRequestDto dto = new CardRequestDto();
//        dto.setQuestionContent("협업 경험은?");
//        dto.setCompanyName("배달의민족");
//        dto.setJob("기획");
//        dto.setStatus(List.of(Status.자유로운));
//        dto.setStrategy("정면 돌파");
//        dto.setAnswerContent("충돌이 있어도 소통으로 해결합니다");
//
//        CardResponseDto card = cardService.create(dto, user.getId());
//
//        StrategyRequestDto strategyDto = new StrategyRequestDto();
//        strategyDto.setStrategy("정면 돌파");
//
//        StrategyResponseDto strategy = strategyService.create(card.getCardId(), strategyDto, user.getId());
//
//        assertThat(strategy.getMessage()).isEqualTo("전략이 등록되었습니다.");
//    }
}

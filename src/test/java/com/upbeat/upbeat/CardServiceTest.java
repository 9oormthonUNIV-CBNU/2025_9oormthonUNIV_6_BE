package com.upbeat.upbeat;

import com.upbeat.upbeat.domain.cardgame.dto.request.*;
import com.upbeat.upbeat.domain.cardgame.dto.response.*;
import com.upbeat.upbeat.domain.cardgame.entity.*;
import com.upbeat.upbeat.domain.cardgame.repository.*;
import com.upbeat.upbeat.domain.cardgame.service.AnswerService;
import com.upbeat.upbeat.domain.cardgame.service.CardService;
import com.upbeat.upbeat.domain.cardgame.service.LikeService;
import com.upbeat.upbeat.domain.cardgame.service.StrategyService;
import com.upbeat.upbeat.domain.user.dto.UserLoginRequestDto;
import com.upbeat.upbeat.domain.user.dto.UserLoginResponseDto;
import com.upbeat.upbeat.domain.user.dto.UserSignupRequestDto;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import com.upbeat.upbeat.domain.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    //InjectMocks
    @Autowired
    private CardService cardService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        for(int i=0;i<3;i++) {
            String userId = "user"+i;
            if (userRepository.findByUserId(userId).isEmpty()) {
                UserSignupRequestDto signupRequestDto = new UserSignupRequestDto();
                signupRequestDto.setUserId(userId);
                signupRequestDto.setPassword("password");
                signupRequestDto.setNickname("닉네임" + i);
                userService.signup(signupRequestDto);
            }

            userRepository.flush();
            entityManager.clear();
        }
    }

    @Test
    @DisplayName("좋아요 3개 누르기")
    public void IncreaseLike() {
        int likes =3;

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .userId("testuser" + i)
                    .password("password")
                    .nickname("테스터" + i)
                    .build();
            userRepository.save(user);
            users.add(user);
        }

        User writer = users.get(0);
        Long writerId = writer.getId();

        List<Status> statusList = Arrays.asList(Status.자유로운,Status.긍정적,Status.다대일);
        CardRequestDto cardRequestDto = new CardRequestDto(
                "동기들 중 본인이 가장 낫다고 생각한 점은 무엇인가요?",
                "카카오","글로벌마케팅",
                statusList,
                "공손하게 회피",""
        );
        CardResponseDto cardResponseDto = cardService.create(cardRequestDto,writerId);

        strategyService.create(cardResponseDto.getCardId(),
                new StrategyRequestDto("공손하게 회피", cardResponseDto.getCardId()), writerId);

        AnswerResponseDto answer = answerService.create(cardResponseDto.getCardId(),
                new AnswerRequestDto("잘 모르겠습니다.", cardResponseDto.getCardId()),
                writerId);

        LikeResponseDto like = null;
        for (User liker : users) {
            like = likeService.create(new LikeRequestDto(answer.getAnswerId()), liker.getId());
        }

        assertThat(like.getLikes()).isEqualTo(likes);
    }

    @Test
    @DisplayName("좋아요 3개 누르고 1개 취소")
    public void DecreaseLike() {
        int likes =2;

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .userId("testuser" + i)
                    .password("password")
                    .nickname("테스터" + i)
                    .build();
            userRepository.save(user);
            users.add(user);
        }

        User writer = users.get(0);
        Long writerId = writer.getId();

        List<Status> statusList = Arrays.asList(Status.자유로운,Status.긍정적,Status.다대일);
        CardRequestDto cardRequestDto = new CardRequestDto(
                "동기들 중 본인이 가장 낫다고 생각한 점은 무엇인가요?",
                "카카오","글로벌마케팅",
                statusList,
                "공손하게 회피","");
        CardResponseDto cardResponseDto = cardService.create(cardRequestDto,writerId);

        strategyService.create(cardResponseDto.getCardId(),
                new StrategyRequestDto("공손하게 회피", cardResponseDto.getCardId()), writerId);

        AnswerResponseDto answer = answerService.create(cardResponseDto.getCardId(),
                new AnswerRequestDto("잘 모르겠습니다.", cardResponseDto.getCardId()),
                writerId
        );

        for (User user : users) {
            likeService.create(new LikeRequestDto(answer.getAnswerId()), user.getId());
        }
        LikeResponseDto like = likeService.delete(answer.getAnswerId(), users.get(2).getId());

        assertThat(like.getLikes()).isEqualTo(likes);
    }
}

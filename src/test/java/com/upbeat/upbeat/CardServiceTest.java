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
    @Mock
    private CardRepository cardRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private StrategyRepository strategyRepository;

    @InjectMocks
    private CardService cardService;
    @InjectMocks
    private AnswerService answerService;
    @InjectMocks
    private LikeService likeService;
    @InjectMocks
    private StrategyService strategyService;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        for(int i=0;i<3;i++) {
            UserSignupRequestDto signupRequestDto = new UserSignupRequestDto();
            signupRequestDto.setUserId("newuser");
            signupRequestDto.setPassword("password");
            signupRequestDto.setNickname("닉네임");
            userService.signup(signupRequestDto);

            UserLoginRequestDto loginRequestDto = new UserLoginRequestDto();
            loginRequestDto.setUserId("testuser");
            loginRequestDto.setPassword("password");
            userService.login(loginRequestDto);
        }
    }

    @Test
    @DisplayName("좋아요 3개 누르기")
    public void IncreaseLike() {
        int likes =3;

        List<Status> statusList = Arrays.asList(Status.자유로운,Status.긍정적,Status.다대일);
        CardRequestDto cardRequestDto = new CardRequestDto("동기들 중 본인이 가장 낫다고 생각한 점은 무엇인가요?","카카오","글로벌마케팅",statusList,"","");
        CardResponseDto cardResponseDto = cardService.create(cardRequestDto,1L);

        StrategyRequestDto strategyRequestDto = new StrategyRequestDto("공손하게 회피",1L);
        StrategyResponseDto strategy = strategyService.create(1L,strategyRequestDto,1L);

        AnswerRequestDto answerRequestDto = new AnswerRequestDto("잘 모르겠습니다.",1L);
        AnswerResponseDto answer = answerService.create(1L,answerRequestDto, 1L);

        LikeResponseDto like = new LikeResponseDto();
        for(Long i=0L;i<3L;i++){
            LikeRequestDto likeRequestDto = new LikeRequestDto(1L);
            like = likeService.create(likeRequestDto, i);
        }

        assertThat(like.getLikes()).isEqualTo(likes);
    }

    @Test
    @DisplayName("좋아요 3개 누르고 1개 취소")
    public void DecreaseLike() {
        int likes =2;

        List<Status> statusList = Arrays.asList(Status.자유로운,Status.긍정적,Status.다대일);
        CardRequestDto cardRequestDto = new CardRequestDto("동기들 중 본인이 가장 낫다고 생각한 점은 무엇인가요?","카카오","글로벌마케팅",statusList,"","");
        CardResponseDto cardResponseDto = cardService.create(cardRequestDto,1L);

        StrategyRequestDto strategyRequestDto = new StrategyRequestDto("공손하게 회피",1L);
        StrategyResponseDto strategy = strategyService.create(1L,strategyRequestDto,1L);

        AnswerRequestDto answerRequestDto = new AnswerRequestDto("잘 모르겠습니다.",1L);
        AnswerResponseDto answer = answerService.create(1L,answerRequestDto, 1L);

        for(Long i=0L;i<3L;i+=1L){
            LikeRequestDto likeRequestDto = new LikeRequestDto(1L);
            likeService.create(likeRequestDto, i);
        }
        LikeResponseDto like =like = likeService.delete(1L,2L);

        assertThat(like.getLikes()).isEqualTo(likes);
    }
}

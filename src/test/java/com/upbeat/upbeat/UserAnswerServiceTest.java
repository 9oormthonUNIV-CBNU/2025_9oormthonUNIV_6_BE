package com.upbeat.upbeat;
import com.upbeat.upbeat.domain.interviewtest.dto.UserAnswerRequestDto;
import com.upbeat.upbeat.domain.interviewtest.entity.Option;
import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import com.upbeat.upbeat.domain.interviewtest.repository.OptionRepository;
import com.upbeat.upbeat.domain.interviewtest.repository.QuestionRepository;
import com.upbeat.upbeat.domain.interviewtest.service.UserAnswerService;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Getter
@SpringBootTest
@Transactional
class UserAnswerServiceTest {

    @Autowired
    private UserAnswerService userAnswerService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    private List<Question> questions;

    @BeforeEach
    void setup() {
        questions = questionRepository.findAll();
    }

    private void submitAnswers(Long userId, String abPattern) {
        for (int i = 0; i < 10; i++) {
            Question question = questions.get(i);
            List<Option> options = question.getOptions();

            String desiredLabel = String.valueOf(abPattern.charAt(i));
            Option selected = options.stream()
                    .filter(o -> o.getLabel().equals(desiredLabel))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("해당 label 옵션이 없습니다: " + desiredLabel));

            UserAnswerRequestDto dto = new UserAnswerRequestDto();
            dto.setQuestionId(question.getId());
            dto.setOptionId(selected.getId());

            userAnswerService.saveUserAnswer(dto, userId);
        }
    }


    @Test
    void TYPE1_두루뭉술_낭만가형() {
        submitAnswers(101L, "AAAAAAAAAA"); // A = 10
        String result = userAnswerService.calculateResultType(101L);
        assertThat(result).isEqualTo("TYPE1");
    }

    @Test
    void TYPE2_과묵한_논리형() {
        submitAnswers(102L, "BBBBBBBBBB"); // B = 10
        String result = userAnswerService.calculateResultType(102L);
        assertThat(result).isEqualTo("TYPE2");
    }

    @Test
    void TYPE3_순간_멘붕러형() {
        // Q3 = A, Q6 = A + 2개 이상 B among 2,8,5,10
        submitAnswers(103L, "BAAABBABBB");
        String result = userAnswerService.calculateResultType(103L);
        assertThat(result).isEqualTo("TYPE3");
    }

    @Test
    void TYPE4_시나리오_고정러형() {
        // Q2, Q8 = B + 3,6,5,10 모두 B
        submitAnswers(104L, "ABBABBABAB");
        String result = userAnswerService.calculateResultType(104L);
        assertThat(result).isEqualTo("TYPE4");
    }

    @Test
    void TYPE5_방어적_자기검열러형() {
        // Q5, Q10 = A + 2,3,6,8 모두 A
        submitAnswers(105L, "AABAAABABA");
        String result = userAnswerService.calculateResultType(105L);
        assertThat(result).isEqualTo("TYPE5");
    }

    @Test
    void TYPE6_혼합형() {
        // 아무 조건도 충족 안 되는 중간값
        submitAnswers(106L, "BABABBBBBB");
        String result = userAnswerService.calculateResultType(106L);
        assertThat(result).isEqualTo("TYPE6");
    }
}

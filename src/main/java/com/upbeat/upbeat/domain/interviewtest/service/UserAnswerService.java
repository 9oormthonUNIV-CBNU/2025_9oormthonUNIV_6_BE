package com.upbeat.upbeat.domain.interviewtest.service;

import com.upbeat.upbeat.domain.interviewtest.dto.ResultTypeResponseDto;
import com.upbeat.upbeat.domain.interviewtest.dto.UserAnswerRequestDto;
import com.upbeat.upbeat.domain.interviewtest.dto.UserAnswerResponseDto;
import com.upbeat.upbeat.domain.interviewtest.entity.Option;
import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import com.upbeat.upbeat.domain.interviewtest.entity.UserAnswer;
import com.upbeat.upbeat.domain.interviewtest.repository.OptionRepository;
import com.upbeat.upbeat.domain.interviewtest.repository.QuestionRepository;
import com.upbeat.upbeat.domain.interviewtest.repository.UserAnswerRepository;
import com.upbeat.upbeat.domain.user.entity.User;
import com.upbeat.upbeat.domain.user.repository.UserRepository;
import com.upbeat.upbeat.global.exception.CustomException;
import com.upbeat.upbeat.global.exception.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final ResultTypeService resultTypeService;
    private final UserRepository userRepository;

    //사용자 응답 저장
    public UserAnswerResponseDto saveUserAnswer(UserAnswerRequestDto dto, Long userId) {
        boolean alreadyExists = userAnswerRepository.existsByUserIdAndQuestionId(userId, dto.getQuestionId());
        if (alreadyExists) {
            throw new IllegalStateException("이미 해당 질문에 답변했습니다. 중복 저장은 불가능합니다.");
        }

        Question question = questionRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문입니다."));
        Option option = optionRepository.findById(dto.getOptionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보기입니다."));

        UserAnswer userAnswer = new UserAnswer();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        userAnswer.setUser(user);
        userAnswer.setQuestion(question);
        userAnswer.setOption(option);

        UserAnswer saved = userAnswerRepository.save(userAnswer);
        return UserAnswerResponseDto.from(saved);
    }

    //사용자 응답 조회
    public List<UserAnswerResponseDto> getUserAnswerDtos(Long userId) {
        return userAnswerRepository.findByUserId(userId).stream()
                .map(UserAnswerResponseDto::from)
                .collect(Collectors.toList());
    }

    public String calculateResultType(Long userId) {
        List<UserAnswer> answers = userAnswerRepository.findByUserId(userId);

        if (answers.size() < 10) {
            throw new CustomException(ErrorCode.NOT_ENOUGH_ANSWERS);
        }

        int aCount = 0;
        int bCount = 0;
        Map<Integer, String> answerMap = new HashMap<>();

        for (UserAnswer ans : answers) {
            String label = ans.getOption().getLabel();
            if ("A".equals(label)) aCount++;
            else if ("B".equals(label)) bCount++;
            answerMap.put(ans.getQuestion().getId().intValue(), label);
        }

        if (aCount >= 9 && bCount <= 1) return "TYPE1";
        if (bCount >= 9 && aCount <= 1) return "TYPE2";
        if ((aCount >= 4 && bCount <= 6) &&
                ("A".equals(answerMap.getOrDefault(3, "")) || "A".equals(answerMap.getOrDefault(6, ""))) &&
                !( // TYPE4 조건 X
                        "B".equals(answerMap.getOrDefault(2, "")) &&
                                "B".equals(answerMap.getOrDefault(8, ""))
                ) &&
                !( // TYPE5 조건 X
                        "A".equals(answerMap.getOrDefault(5, "")) ||
                                "A".equals(answerMap.getOrDefault(10, ""))
                )) {
            return "TYPE3";
        }

        if ((aCount >= 4 && bCount <= 6) &&
                "B".equals(answerMap.getOrDefault(2, "")) && "B".equals(answerMap.getOrDefault(8, "")))
            return "TYPE4";

        if ((aCount >= 4 && bCount <= 6) &&
                ("A".equals(answerMap.getOrDefault(5, "")) || "A".equals(answerMap.getOrDefault(10, ""))))
            return "TYPE5";

        return "TYPE6";
    }

    public ResultTypeResponseDto getUserResultType(Long userId) {
        String typeCode = calculateResultType(userId);
        return resultTypeService.getResultByTypeCode(typeCode);
    }
    //url매핑으로 인해 새로 추가한 코드
    public Map<String, String> getRedirectUrlByUserResult(Long userId) {
        String typeCode = calculateResultType(userId);
        String redirectUrl = "/results/" + typeCode.toLowerCase() + ".html";

        Map<String, String> result = new HashMap<>();
        result.put("redirectUrl", redirectUrl);
        return result;
    }
}

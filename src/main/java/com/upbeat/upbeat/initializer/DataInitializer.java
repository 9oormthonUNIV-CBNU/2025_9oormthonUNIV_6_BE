package com.upbeat.upbeat.initializer;

import com.upbeat.upbeat.domain.interviewtest.entity.Option;
import com.upbeat.upbeat.domain.interviewtest.entity.Question;
import com.upbeat.upbeat.domain.interviewtest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        Question q1 = new Question();
        q1.setQuestion("1. 자기소개 질문을 받았을 때 나는…");

        Option o1 = new Option(null, "A", "두루뭉술하게 전체 인생 흐름을 말하게 된다", q1);
        Option o2 = new Option(null, "B", "직무와 관련된 키워드를 중심으로 간결히 말한다", q1);

        q1.setOptions(List.of(o1, o2));

        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setQuestion("2. 내가 경험을 설명할 때는…");
        Option q2_a = new Option(null, "A", "멋져 보이는 단어로 꾸미는 편이다", q2);
        Option q2_b = new Option(null, "B", "구체적인 상황, 숫자, 결과를 강조한다", q2);
        q2.setOptions(List.of(q2_a, q2_b));
        questionRepository.save(q2);

        Question q3 = new Question();
        q3.setQuestion("3. 압박 질문이 나왔을 때 나는…");
        Option q3_a = new Option(null, "A", "순간 멘붕이 오고, 머릿속이 하얘진다", q3);
        Option q3_b = new Option(null, "B", "당황하더라도 일단 말하고 정리해간다", q3);
        q3.setOptions(List.of(q3_a, q3_b));
        questionRepository.save(q3);

        Question q4 = new Question();
        q4.setQuestion("4. 마지막 질문 “하고 싶은 말?”이 나오면…");
        Option q4_a = new Option(null, "A", "준비한 말을 꺼내다가도 어버버한다", q4);
        Option q4_b = new Option(null, "B", "핵심 한 줄로 인상을 남기려 한다", q4);
        q4.setOptions(List.of(q4_a, q4_b));
        questionRepository.save(q4);

        Question q5 = new Question();
        q5.setQuestion("5. 나의 말버릇은…");
        Option q5_a = new Option(null, "A", "“어… 음…” 같은 추임새가 자주 나온다", q5);
        Option q5_b = new Option(null, "B", "말을 시작할 땐 침착하게 정리된 문장으로 시작한다", q5);
        q5.setOptions(List.of(q5_a, q5_b));
        questionRepository.save(q5);

        Question q6 = new Question();
        q6.setQuestion("6. 예상 못한 질문을 받았을 때 나는…");
        Option q6_a = new Option(null, "A", "너무 솔직하거나 당황스런 말을 해버린 적 있다", q6);
        Option q6_b = new Option(null, "B", "돌려 말하거나 일단 맥락부터 찾으려 노력한다", q6);
        q6.setOptions(List.of(q6_a, q6_b));
        questionRepository.save(q6);

        Question q7 = new Question();
        q7.setQuestion("7. 내가 말한 뒤에 가장 자주 드는 생각은…");
        Option q7_a = new Option(null, "A", "‘내가 왜 그렇게 말했지?’", q7);
        Option q7_b = new Option(null, "B", "‘그래도 말은 이어갔네’", q7);
        q7.setOptions(List.of(q7_a, q7_b));
        questionRepository.save(q7);

        Question q8 = new Question();
        q8.setQuestion("8. 면접 전에 나는…");
        Option q8_a = new Option(null, "A", "전체 시나리오를 외우듯 준비한다", q8);
        Option q8_b = new Option(null, "B", "키워드 중심으로 말 흐름만 정리한다", q8);
        q8.setOptions(List.of(q8_a, q8_b));
        questionRepository.save(q8);

        Question q9 = new Question();
        q9.setQuestion("9. 나는 보통 말을…");
        Option q9_a = new Option(null, "A", "길게 늘어지게 하는 편이다", q9);
        Option q9_b = new Option(null, "B", "짧고 핵심 있게 말하려 한다", q9);
        q9.setOptions(List.of(q9_a, q9_b));
        questionRepository.save(q9);

        Question q10 = new Question();
        q10.setQuestion("10. 나의 말투는…");
        Option q10_a = new Option(null, "A", "방어적이고 자신 없어 보인다는 말을 들은 적 있다", q10);
        Option q10_b = new Option(null, "B", "침착하지만 건조하게 들릴 수 있다", q10);
        q10.setOptions(List.of(q10_a, q10_b));
        questionRepository.save(q10);
    }
}

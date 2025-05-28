package com.upbeat.upbeat.domain.cardgame.dto.request;

import com.upbeat.upbeat.domain.cardgame.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor //생성자
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CardRequestDto {
    @Schema(description = "질문 내용", example="동기들 중 본인이 가장 낫다고 생각한 점은 무엇인가요?")
    @NotBlank(message = "질문 내용은 필수입니다.")
    @Pattern(regexp = "^.{0,30}$", message = "30자 이하로 입력해주세요.")
    private String questionContent;
    @Schema(description = "기업명", example="카카오")
    @NotBlank(message = "기업명은 필수입니다.")
    @Pattern(regexp = "^.{0,30}$", message = "30자 이하로 입력해주세요.")
    private String companyName;
    @Schema(description = "직무", example="글로벌마케팅")
    @NotBlank(message = "직무는 필수입니다.")
    @Pattern(regexp = "^.{0,30}$", message = "30자 이하로 입력해주세요.")
    private String job;
    @Schema(description = "상황 키워드", example="[\"자유로운\",\"다대일\",\"긍정적\"]")
    @NotBlank(message = "상황 키워드는 필수입니다.")
    private List<Status> status;

    @Schema(description = "답변 전략", example="공손하게 회피")
    private String strategy;
    @Schema(description = "답변 내용", example="동기마다 강점이 달라 조심스럽습니다. 저는 성장하는 자세가 강점입니다.")
    @Pattern(regexp = "^.{0,30}$", message = "30자 이하로 입력해주세요.")
    private String answerContent;

//    @Schema(description = "유저 Id")
//    @NotNull(message = "유저 Id는 필수입니다.")
//    private Long user_id;
}

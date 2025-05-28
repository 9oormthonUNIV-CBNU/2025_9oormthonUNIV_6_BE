package com.upbeat.upbeat.domain.cardgame.entity;

public enum AnswerStrategy {
    AVOIDANCE("공손하게 회피"),
    BREAKTHROUGH("정면 돌파"),
    HUMOR("유머 활용"),
    RECONFIRMATION("질문 재확인 후 답변");

    private final String description;

    AnswerStrategy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AnswerStrategy fromDescription(String description) {
        for (AnswerStrategy strategy : AnswerStrategy.values()) {
            if (strategy.getDescription().equals(description)) {
                return strategy;
            }
        }
        throw new IllegalArgumentException("올바르지 않은 전략입니다: " + description);
    }
}

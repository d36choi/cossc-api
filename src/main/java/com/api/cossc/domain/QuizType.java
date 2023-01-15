package com.api.cossc.domain;

import java.util.Arrays;

public enum QuizType {
    MULTIPLE_CHOICE("multiple_choice"),
    OX("ox"),
    UNKNOWN("unknown")
    ;

    private final String name;

    QuizType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static QuizType getQuizTypeByName(String name) {

        return Arrays.stream(QuizType.values())
                .filter(q -> q.name().equals(name))
                .findFirst()
                .orElse(UNKNOWN);
    }
}

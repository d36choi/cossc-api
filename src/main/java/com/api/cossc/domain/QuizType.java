package com.api.cossc.domain;

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
}

package com.api.cossc.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class QuizTypeConverter implements AttributeConverter<String, QuizType> {
    @Override
    public QuizType convertToDatabaseColumn(String attribute) {
        if (StringUtils.isEmpty(attribute)) {
            return null;
        }

        return Arrays.stream(QuizType.values())
                .filter(q -> q.getName().equals(attribute))
                .findFirst()
                .orElse(QuizType.UNKNOWN);
    }

    @Override
    public String convertToEntityAttribute(QuizType dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }

        return Arrays.stream(QuizType.values())
                .filter(q -> q.getName().equals(dbData.getName()))
                .findFirst()
                .map(QuizType::getName)
                .orElse(QuizType.UNKNOWN.getName());
    }
}

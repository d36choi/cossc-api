package com.api.cossc.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class QuizTypeConverter implements AttributeConverter<QuizType, String> {
    @Override
    public String convertToDatabaseColumn(QuizType attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }

        return Arrays.stream(QuizType.values())
                .filter(q -> q.getName().equals(attribute.getName()))
                .findFirst()
                .map(QuizType::getName)
                .orElse(QuizType.UNKNOWN.getName());
    }

    @Override
    public QuizType convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return null;
        }

        return Arrays.stream(QuizType.values())
                .filter(q -> q.getName().equals(dbData))
                .findFirst()
                .orElse(QuizType.UNKNOWN);
    }

}

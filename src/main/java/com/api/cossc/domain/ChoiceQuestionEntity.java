package com.api.cossc.domain;

import com.api.cossc.service.choice.ChoiceQuestionService;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ChoiceQuestionEntity {


    public abstract void add(ChoiceQuestionService choiceQuestionService);
}

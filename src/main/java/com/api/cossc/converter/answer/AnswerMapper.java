package com.api.cossc.converter.answer;

import com.api.cossc.domain.AnswerEntity;
import com.api.cossc.dto.answer.AnswerCreationRequest;
import com.api.cossc.dto.answer.AnswerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);
    AnswerResponse.AnswerItem toAnswerItem(AnswerEntity answer);


    AnswerEntity toAnswerEntity(AnswerCreationRequest answerCreationRequest);
}

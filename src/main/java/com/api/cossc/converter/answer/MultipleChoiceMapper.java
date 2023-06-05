package com.api.cossc.converter.answer;

import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import com.api.cossc.dto.choice.MultipleChoiceQuestionCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MultipleChoiceMapper {

    MultipleChoiceMapper INSTANCE = Mappers.getMapper(MultipleChoiceMapper.class);
//    AnswerResponse.AnswerItem toAnswerItem(AnswerEntity answer);


    MultipleChoiceQuestionEntity toMultipleChoiceQuestionEntity(MultipleChoiceQuestionCreationRequest multipleChoiceQuestionCreationRequest);
}

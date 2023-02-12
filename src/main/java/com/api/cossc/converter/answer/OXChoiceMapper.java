package com.api.cossc.converter.answer;

import com.api.cossc.domain.OXChoiceQuestionEntity;
import com.api.cossc.dto.choice.OXChoiceQuestionCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OXChoiceMapper {

    OXChoiceMapper INSTANCE = Mappers.getMapper(OXChoiceMapper.class);

    OXChoiceQuestionEntity toEntity(OXChoiceQuestionCreationRequest oxChoiceQuestionCreationRequest);

}

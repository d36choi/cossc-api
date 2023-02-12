package com.api.cossc.service.repository;

import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestionEntity, Long> {
}

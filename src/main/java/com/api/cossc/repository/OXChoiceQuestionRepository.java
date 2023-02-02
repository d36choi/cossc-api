package com.api.cossc.repository;

import com.api.cossc.domain.OXChoiceQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OXChoiceQuestionRepository extends JpaRepository<OXChoiceQuestionEntity, Long> {
}

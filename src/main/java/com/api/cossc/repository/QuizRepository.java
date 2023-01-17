package com.api.cossc.repository;

import com.api.cossc.domain.QuizEntity;
import com.api.cossc.domain.QuizType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    List<QuizEntity> findAllByType(QuizType type);
    List<QuizEntity> findAllByTagEntity_Id(Long id);

}

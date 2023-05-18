package com.api.cossc.repository;

import com.api.cossc.domain.DailyQuizEntity;
import com.api.cossc.domain.DailyQuizId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyQuizRepository extends JpaRepository<DailyQuizId, DailyQuizEntity> {

}

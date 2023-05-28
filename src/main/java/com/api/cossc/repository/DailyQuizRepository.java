package com.api.cossc.repository;

import com.api.cossc.domain.DailyQuizEntity;
import com.api.cossc.domain.DailyQuizId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyQuizRepository extends JpaRepository<DailyQuizEntity, DailyQuizId>, DailyQuizRepositoryCustom {

    List<DailyQuizEntity> findAllByDailyQuizId_UserIdAndDailyQuizId_GivenDate(Long uid, LocalDate givenDate);
}

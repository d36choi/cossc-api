package com.api.cossc.repository;

import com.api.cossc.domain.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    List<HistoryEntity> findAllByUserEntity_Id(Long userId);
}

package com.api.cossc.repository;

import java.util.List;

public interface DailyQuizRepositoryCustom {

    List<Long> getAllQuizIdByUser(Long uid);
}

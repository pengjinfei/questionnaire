package com.pengjinfei.questionnaire.dao;

import com.pengjinfei.questionnaire.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pengjinfei on 2016/12/19.
 * Description:
 */
public interface ScoreRepository extends JpaRepository<Score,Long> {
}

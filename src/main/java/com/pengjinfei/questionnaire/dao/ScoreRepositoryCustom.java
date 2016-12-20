package com.pengjinfei.questionnaire.dao;

import com.pengjinfei.questionnaire.domain.Score;

import java.util.List;

/**
 * Created by pengjinfei on 2016/12/20.
 * Description:
 */
public interface ScoreRepositoryCustom {

    List<Score> getAggrateResult();

}

package com.pengjinfei.questionnaire.dao.impl;

import com.pengjinfei.questionnaire.dao.ScoreRepositoryCustom;
import com.pengjinfei.questionnaire.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengjinfei on 2016/12/20.
 * Description:
 */
public class ScoreRepositoryImpl implements ScoreRepositoryCustom {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Score> getAggrateResult() {
        List resultList = entityManager.createQuery("select name,avg(res),count(res)  from Score group by name").getResultList();
        List<Score> scores = new ArrayList<>();
        for (Object object : resultList) {
            Object[] objects= (Object[]) object;
            Score score = new Score();
            score.setName(String.valueOf(objects[0]));
            score.setRes((int)Double.parseDouble(String.valueOf(objects[1])));
            score.setScoreDetail(String.valueOf(objects[2]));
            scores.add(score);
        }
        return scores;
    }
}

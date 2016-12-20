package com.pengjinfei.questionnaire.dao;

import com.pengjinfei.questionnaire.domain.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pengjinfei on 2016/12/19.
 * Description:
 */
public interface ScoreRepository extends CrudRepository<Score,Long>,ScoreRepositoryCustom {

    List<Score> findByName(String name);

    List<Score> findByNameAndSuggestionNotNull(String name);
}

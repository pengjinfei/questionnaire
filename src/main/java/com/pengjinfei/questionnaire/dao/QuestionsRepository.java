package com.pengjinfei.questionnaire.dao;

import com.pengjinfei.questionnaire.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Pengjinfei on 2016/12/18.
 * Description:
 */
public interface QuestionsRepository extends JpaRepository<Questions,Long> {
}

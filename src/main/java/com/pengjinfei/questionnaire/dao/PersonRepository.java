package com.pengjinfei.questionnaire.dao;

import com.pengjinfei.questionnaire.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Pengjinfei on 2016/12/18.
 * Description:
 */
public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findByDepartment(String department);
}

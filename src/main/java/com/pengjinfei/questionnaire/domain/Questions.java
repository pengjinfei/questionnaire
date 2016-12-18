package com.pengjinfei.questionnaire.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Pengjinfei on 2016/12/18.
 * Description:
 */
@Entity
public class Questions {

    @Id
    @GeneratedValue
    private Long id;

    private String question;

    public Questions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

/*
 * TODO Copyright
 */

package com.company.survey.entity.reference;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NamePattern("%s|code")
@Table(name = "SURVEY_POSSIBLE_ANSWER")
@Entity(name = "survey$PossibleAnswer")
public class PossibleAnswer extends StandardEntity {
    private static final long serialVersionUID = -971836715562824660L;

    @Column(name = "CODE")
    protected String code;

    @Column(name = "ANSWER_TEXT")
    protected String answerText;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "QUESTION_ID")
    protected Question question;

    @Column(name = "ACTIVE")
    protected Boolean active;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }


    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }


}
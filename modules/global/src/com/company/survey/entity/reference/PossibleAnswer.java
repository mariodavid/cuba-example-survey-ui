/*
 * TODO Copyright
 */

package com.company.survey.entity.reference;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|answerText")
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
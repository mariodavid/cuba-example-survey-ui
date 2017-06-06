/*
 * TODO Copyright
 */

package com.company.survey.entity.survey;

import com.company.survey.entity.reference.PossibleAnswer;
import com.company.survey.entity.reference.Question;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NamePattern("%s: %s|question,answer")
@Table(name = "SURVEY_SELECTED_ANSWER")
@Entity(name = "survey$SelectedAnswer")
public class SelectedAnswer extends StandardEntity {
    private static final long serialVersionUID = 125328975667934747L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "QUESTION_ID")
    protected Question question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ANSWER_ID")
    protected PossibleAnswer answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FILLED_SURVEY_ID")
    protected FilledSurvey filledSurvey;

    @Column(name = "COMMENT_")
    protected String comment;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setAnswer(PossibleAnswer answer) {
        this.answer = answer;
    }

    public PossibleAnswer getAnswer() {
        return answer;
    }

    public void setFilledSurvey(FilledSurvey filledSurvey) {
        this.filledSurvey = filledSurvey;
    }

    public FilledSurvey getFilledSurvey() {
        return filledSurvey;
    }


}
/*
 * TODO Copyright
 */

package com.company.survey.entity.reference;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.company.survey.entity.reference.PossibleAnswer;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.Set;
import javax.persistence.OneToMany;

@NamePattern("%s|questionText")
@Table(name = "SURVEY_QUESTION")
@Entity(name = "survey$Question")
public class Question extends StandardEntity {
    private static final long serialVersionUID = 5575080743068577511L;

    @Column(name = "QUESTION_TEXT")
    protected String questionText;


    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "question")
    protected Set<PossibleAnswer> possibleAnswers;

    public void setPossibleAnswers(Set<PossibleAnswer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public Set<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }


    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }



}
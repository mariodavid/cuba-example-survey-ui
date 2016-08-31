/*
 * TODO Copyright
 */

package com.company.survey.entity.reference;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.company.survey.entity.reference.Question;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|title")
@Table(name = "SURVEY_SURVEY")
@Entity(name = "survey$Survey")
public class Survey extends StandardEntity {
    private static final long serialVersionUID = -3507332839740606644L;

    @Column(name = "TITLE", nullable = false)
    protected String title;

    @JoinTable(name = "SURVEY_SURVEY_QUESTION_LINK",
        joinColumns = @JoinColumn(name = "SURVEY_ID"),
        inverseJoinColumns = @JoinColumn(name = "QUESTION_ID"))
    @ManyToMany
    protected Set<Question> questions;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Question> getQuestions() {
        return questions;
    }


}
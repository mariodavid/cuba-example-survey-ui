package com.company.survey.entity.reference;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "SURVEY_QUESTION_CATEGORY")
@Entity(name = "survey$QuestionCategory")
public class QuestionCategory extends StandardEntity {
    private static final long serialVersionUID = 7404944882434777834L;

    @Column(name = "NAME")
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
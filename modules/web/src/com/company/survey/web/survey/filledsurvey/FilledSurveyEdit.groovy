package com.company.survey.web.survey.filledsurvey

import com.company.survey.entity.reference.PossibleAnswer;
import com.company.survey.entity.reference.Survey
import com.company.survey.entity.survey.Answer
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.survey.entity.survey.FilledSurvey;
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.Frame
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.components.PickerField
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.data.CollectionDatasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;

public class FilledSurveyEdit extends AbstractEditor<FilledSurvey> {

    @Inject
    UserSession userSession;


    @Named("fieldGroup.survey")
    PickerField survey;

    @Inject
    CollectionDatasource<Answer, UUID> answersDs

    @Inject
    CollectionDatasource<PossibleAnswer, UUID> questionsPossibleAnswersDs


    @Inject
    Metadata metadata


    @Inject
    Table answersTable





    @Inject
    protected ComponentsFactory componentsFactory;

    @Override
    protected void initNewItem(FilledSurvey item) {

        item.filledDate = new Date()
        item.user = userSession.user

        item.answers = []

        survey.addValueChangeListener(new Component.ValueChangeListener() {
            @Override
            public void valueChanged(Component.ValueChangeEvent e) {

                FilledSurvey filledSurvey = getItem()
                Survey survey = (Survey) e.getValue();

                survey.questions.each {
                    Answer answer = metadata.create(Answer.class)
                    answer.question = it
                    answer.filledSurvey = filledSurvey

                    filledSurvey.answers << answer
                    answersDs.addItem(answer)
                }
            }
        });
        super.initNewItem(item)
    }


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);




        answersTable.addGeneratedColumn("filteredAnswer", new Table.ColumnGenerator<Answer>() {

            @Override
            Component generateCell(Answer entity) {

                LookupPickerField field = componentsFactory.createComponent(LookupPickerField.NAME);

                /*
                    All PossibleAnswers should be displayed that belong to this Question (entity.question)
                */
                field.setDatasource(answersTable.getItemDatasource(entity), "answer");
                field.setOptionsDatasource(questionsPossibleAnswersDs);

                showNotification("${entity.question.possibleAnswers*.answerText.join(", ")}", Frame.NotificationType.TRAY)


                field.addLookupAction();
                field.addOpenAction();
                return field;
            }
        })
    }
}
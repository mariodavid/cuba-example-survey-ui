/*
 * TODO Copyright
 */

package com.company.survey.web.survey.filledsurvey

import com.company.survey.entity.reference.PossibleAnswer
import com.company.survey.entity.reference.Survey
import com.company.survey.entity.survey.FilledSurvey
import com.company.survey.entity.survey.SelectedAnswer
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.View
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.data.CollectionDatasource
import com.haulmont.cuba.gui.data.DsBuilder
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import com.haulmont.cuba.security.global.UserSession

import javax.inject.Inject
import javax.inject.Named

public class FilledSurveyOptionGroupsEdit extends AbstractEditor<FilledSurvey> {

    @Inject
    UserSession userSession;


    @Named("fieldGroup.survey")
    PickerField survey;

    @Inject
    CollectionDatasource<SelectedAnswer, UUID> answersDs

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
                    SelectedAnswer answer = metadata.create(SelectedAnswer.class)
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




        answersTable.addGeneratedColumn("answer", new Table.ColumnGenerator<SelectedAnswer>() {

            @Override
            Component generateCell(SelectedAnswer entity) {

                OptionsGroup field = componentsFactory.createComponent(OptionsGroup.NAME);
                field.orientation = OptionsGroup.Orientation.HORIZONTAL
                field.setDatasource(answersTable.getItemDatasource(entity), "answer")
                CollectionDatasource optionsDs = new DsBuilder(getDsContext())
                        .setJavaClass(PossibleAnswer.class)
                        .setViewName(View.MINIMAL)
                        .buildCollectionDatasource()
                optionsDs.setQuery('select e from survey$PossibleAnswer e where e.question.id = :custom$question')
                optionsDs.refresh(['question': entity.question])

                field.setOptionsDatasource(optionsDs);

                return field;
            }
        })
    }
}
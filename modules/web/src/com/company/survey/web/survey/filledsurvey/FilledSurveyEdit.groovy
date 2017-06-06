package com.company.survey.web.survey.filledsurvey

import com.company.survey.entity.reference.PossibleAnswer
import com.company.survey.entity.reference.Survey
import com.company.survey.entity.survey.FilledSurvey
import com.company.survey.entity.survey.SelectedAnswer
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.View
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.data.CollectionDatasource
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.data.DsBuilder
import com.haulmont.cuba.gui.data.GroupDatasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import com.haulmont.cuba.security.global.UserSession
import com.vaadin.data.Item
import com.vaadin.ui.OptionGroup

import javax.inject.Inject
import javax.inject.Named

public class FilledSurveyEdit extends AbstractEditor<FilledSurvey> {

    @Inject
    UserSession userSession;


    @Named("fieldGroup.survey")
    PickerField survey;

    @Inject
    GroupDatasource<SelectedAnswer, UUID> answersDs


    @Inject
    Metadata metadata


    @Inject
    GroupTable answersTable


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

                answersTable.expandAll()
            }
        });
        super.initNewItem(item)
    }


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);



        answersTable.addGeneratedColumn("Question", new Table.ColumnGenerator<SelectedAnswer>() {


            @Override
            Component generateCell(SelectedAnswer entity) {
                PopupView popupView = componentsFactory.createComponent(PopupView);


                def layout = componentsFactory.createComponent(HBoxLayout);
                popupView.popupVisible = false
                popupView.hideOnMouseOut = false
                layout.spacing = true

                TextArea textarea = componentsFactory.createComponent(TextArea);
                textarea.setWidth("600px")
                textarea.setHeight("400px")
                textarea.setEditable(false)
                textarea.setValue(entity.question.description)

                popupView.popupContent = textarea



                LinkButton button = componentsFactory.createComponent(LinkButton.NAME);
                button.caption = entity.question.questionText
                button.alignment = Component.Alignment.MIDDLE_RIGHT
                button.action = new BaseAction('test') {
                    @Override
                    void actionPerform(Component component) {
                        popupView.setPopupVisible(true)
                    }
                }
                layout.add(button)
                layout.add(popupView)
                return layout
            }
        });



        answersTable.addGeneratedColumn("Answer", new Table.ColumnGenerator<SelectedAnswer>() {

            @Override
            Component generateCell(SelectedAnswer entity) {

                OptionsGroup field = componentsFactory.createComponent(OptionsGroup.NAME);
                field.orientation = OptionsGroup.Orientation.HORIZONTAL
                field.setDatasource(answersTable.getItemDatasource(entity), "answer")
                CollectionDatasource optionsDs = new DsBuilder(getDsContext())
                        .setJavaClass(PossibleAnswer.class)
                        .setViewName('possibleAnswer-caption-view')
                        .buildCollectionDatasource()
                optionsDs.setQuery('select e from survey$PossibleAnswer e where e.question.id = :custom$question order by e.code asc')
                optionsDs.refresh(['question': entity.question])

                field.setCaptionProperty('answerText')
                field.setOptionsDatasource(optionsDs);
                OptionGroup optionGroup = field.unwrap(OptionGroup)
                def ids = optionGroup.getItemIds()

                Item item = optionGroup.getItem(ids.first())
                ids.each { itemId ->

                    PossibleAnswer possibleAnswer = entity.question.possibleAnswers.find { it.id == itemId}

                    if (!possibleAnswer.active) {
                        optionGroup.setItemEnabled(itemId, false)
                    }

                }
                return field;
            }
        })


        answersTable.addGeneratedColumn("Result", new Table.ColumnGenerator<SelectedAnswer>() {

            @Override
            Component generateCell(SelectedAnswer entity) {

                Label field = componentsFactory.createComponent(Label.NAME);

                Datasource<SelectedAnswer> ds = answersTable.getItemDatasource(entity)

                field.styleName = 'bold'
                field.setDatasource(ds, 'answer')
                return field;
            }
        })



        answersTable.addGeneratedColumn("Comment", new Table.ColumnGenerator<SelectedAnswer>() {

            @Override
            Component generateCell(SelectedAnswer entity) {
                PopupView popupView = componentsFactory.createComponent(PopupView);

                def layout = componentsFactory.createComponent(HBoxLayout);
                popupView.popupVisible = false
                popupView.hideOnMouseOut = false


                TextArea textarea = componentsFactory.createComponent(TextArea);
                textarea.setWidth("400px")
                textarea.setHeight("200px")

                Datasource<SelectedAnswer> ds = answersTable.getItemDatasource(entity)
                textarea.setDatasource(ds, 'comment')
                popupView.popupContent = textarea


                LinkButton button = componentsFactory.createComponent(LinkButton.NAME);

                button.caption = getCommentCaption(entity)
                button.action = new BaseAction('test') {
                    @Override
                    void actionPerform(Component component) {
                        popupView.setPopupVisible(true)
                    }
                }

                popupView.addPopupVisibilityListener(new PopupView.PopupVisibilityListener() {
                    @Override
                    void popupVisibilityChange(PopupView.PopupVisibilityEvent popupVisibilityEvent) {
                        button.caption = getCommentCaption(entity)
                    }
                })

                layout.add(button)
                layout.add(popupView)
                return layout
            }
        })


    }

    @Override
    void ready() {
        super.ready()
        answersTable.expandAll()
    }

    String getCommentCaption(SelectedAnswer answer) {

        if (answer.comment) {
            if (answer.comment.size() > 10) {
                answer.comment[0..9] + "..."
            } else {
                answer.comment
            }
        }
        else {
            '...'
        }
    }
}
package com.company.survey.web.survey.filledsurvey;

import com.company.survey.entity.survey.FilledSurvey;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import java.util.Map;

public class FilledSurveyBrowse extends AbstractLookup {

    @Inject
    Table<FilledSurvey> filledSurveysTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }

    public void editOptionsGroup() {
        FilledSurvey filledSurvey = filledSurveysTable.getSingleSelected();
        openEditor("survey$FilledSurveyOptionsGroup.edit", filledSurvey, WindowManager.OpenType.NEW_TAB);
    }
}
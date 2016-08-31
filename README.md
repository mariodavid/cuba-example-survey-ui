# cuba-example-survey-ui
CUBA platform example that allows the user to take surveys.

The entities for the reference data are:

* Survey
* Question
* PossibleAnswer


The entities for the survey data are:

* FilledSurvey
* Answer


The main point of this example is to show a situation of how to handle a situation that within a Editor (FilledSurvey) all "answers" 
are shown and editable. One Answer has a reference to a Question that itself has a composition association to PossibleAnswer. 

In the editor of the FilledSurvey all answer instances are generated from all questions that relate to the selected survey.
The answer for each question can be one of the possibleAnswers for this question. This means that the optionsDatasource has to be set in the column for the answer.


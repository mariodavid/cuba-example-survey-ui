# cuba-example-survey-ui
CUBA platform example that allows the user to take surveys.

The entities for the reference data are:

* Survey
* Question
* PossibleAnswer


The entities for the survey data are:

* FilledSurvey
* SelectedAnswer


The main point of this example is to show a situation of how to handle a situation that within a Editor (FilledSurvey) all "selectedAnswers"
are shown and editable. One SelectedAnswer has a reference to a Question. Additionally a SelectedAnswer has a reference
to a PossibleAnswer to store what the user selected for a given Question.

Due to this, also the Question needs to have a composition of PossibleAnswers to chose from for a particular Question.

In the editor of the FilledSurvey all selectedAnswer instances are generated from all questions that relate to the selected survey.
The answer for each question can be one of the possibleAnswers for this question.

In the UI for the FilledSurvey (the Editor) it means that the optionsDatasource has to be set in the column for the answer.


package edu.iu.surveytool.model;

public class SurveyQuestionResponseModel {
	private int survey_id;
	
	private QuestionResponseModel questionResponse;

	public int getSurvey_id() {
		return survey_id;
	}

	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}

	public QuestionResponseModel getQuestionResponse() {
		return questionResponse;
	}

	public void setQuestionResponse(QuestionResponseModel questionResponse) {
		this.questionResponse = questionResponse;
	}
}

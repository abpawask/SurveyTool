package edu.iu.surveytool.model;

import java.util.List;

import edu.iu.surveytool.model.web.QuestionResponseSubmitModel;

public class SurveyQuestionResponseSubmitModel {
	private int surveyId;
	
	private List<QuestionResponseModel> questionResponses;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public List<QuestionResponseModel> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(List<QuestionResponseModel> questionResponses) {
		this.questionResponses = questionResponses;
	}
	
	
}

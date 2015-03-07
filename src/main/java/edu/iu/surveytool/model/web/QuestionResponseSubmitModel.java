package edu.iu.surveytool.model.web;

import java.util.List;

import edu.iu.surveytool.model.QuestionResponseModel;

public class QuestionResponseSubmitModel {
	private List<QuestionResponseModel> questionResponses;

	public List<QuestionResponseModel> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(List<QuestionResponseModel> questionResponses) {
		this.questionResponses = questionResponses;
	}
}

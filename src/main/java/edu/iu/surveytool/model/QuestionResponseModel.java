package edu.iu.surveytool.model;

import java.util.List;

import edu.iu.surveytool.model.basic.Response;

public class QuestionResponseModel {
	private int question_id;
	
	private List<Response> responses;

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	
	
	
}

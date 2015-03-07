package edu.iu.surveytool.services;

import edu.iu.surveytool.model.SurveyQuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseSubmitModel;

public interface ResponseService {
	SurveyQuestionResponseSubmitModel submitResponse(SurveyQuestionResponseSubmitModel surveyQuestionResponseSubmitModel);
	
	SurveyQuestionResponseModel seeResponses(SurveyQuestionResponseModel surveyQuestionResponseModel);
}

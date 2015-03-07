package edu.iu.surveytool.daos;

import edu.iu.surveytool.model.SurveyQuestionResponseModel;

public interface ResponseDAO {

	SurveyQuestionResponseModel submitResponse(SurveyQuestionResponseModel surveyQuestionResponseModel);
	
	SurveyQuestionResponseModel seeResponses(SurveyQuestionResponseModel surveyQuestionResponseModel);
}

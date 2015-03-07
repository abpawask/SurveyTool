package edu.iu.surveytool.services;

import java.util.List;

import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;

public interface SurveyService {
	List<Survey> getSurveys();
	
	List<Question> getQuestionsInSurvey(int surveyId);
}

package edu.iu.surveytool.daos;

import java.util.List;

import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;

public interface SurveyDAO {
	List<Survey> getSurveys();
	
	List<Question> getQuestionsInSurvey(int surveyId);
}

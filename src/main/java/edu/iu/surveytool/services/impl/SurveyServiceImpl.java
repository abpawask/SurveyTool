package edu.iu.surveytool.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iu.surveytool.daos.SurveyDAO;
import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;
import edu.iu.surveytool.services.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyDAO surveyDAO;
	
	public void setSurveyDAO(SurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}

	public List<Survey> getSurveys() {
		
		return surveyDAO.getSurveys();
	}

	public List<Question> getQuestionsInSurvey(int surveyId) {
		
		return surveyDAO.getQuestionsInSurvey(surveyId);
	}

}

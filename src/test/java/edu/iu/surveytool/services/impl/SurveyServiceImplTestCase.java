package edu.iu.surveytool.services.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import edu.iu.surveytool.daos.SurveyDAO;
import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;



public class SurveyServiceImplTestCase{

	private SurveyServiceImpl surveyService;
	
	private SurveyDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(SurveyDAO.class);
		surveyService = new SurveyServiceImpl();
		surveyService.setSurveyDAO(dao);
	}

	@Test
	public void testGetSurveys() {
		List<Survey> expectedSurveys = new ArrayList<Survey>();
		expectedSurveys.add(new Survey());
		expectedSurveys.add(new Survey());
		
		Mockito.when(dao.getSurveys()).thenReturn(expectedSurveys);
		
		List<Survey> surveys = surveyService.getSurveys();
		
		Mockito.verify(dao, Mockito.times(1)).getSurveys();
		
		Assert.assertNotNull(surveys);
		Assert.assertEquals(expectedSurveys.size(), surveys.size());
		Assert.assertEquals(expectedSurveys, surveys);
	}

	@Test
	public void testGetQuestionsInSurvey() {
		
		ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		List<Question> expectedQuestions = new ArrayList<Question>();
		expectedQuestions.add(new Question());
		expectedQuestions.add(new Question());
		
		int survey_id =1;
		
		Mockito.when(dao.getQuestionsInSurvey(survey_id)).thenReturn(expectedQuestions);
		
		List<Question> questions = surveyService.getQuestionsInSurvey(survey_id);
		
		Mockito.verify(dao, Mockito.times(1)).getQuestionsInSurvey(argumentCaptor.capture());
		
		Assert.assertEquals(survey_id, argumentCaptor.getValue().intValue());
		Assert.assertNotNull(questions);
		Assert.assertEquals(expectedQuestions.size(), questions.size());
		Assert.assertEquals(expectedQuestions, questions);
		
	}

}

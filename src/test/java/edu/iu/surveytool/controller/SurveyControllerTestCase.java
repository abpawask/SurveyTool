package edu.iu.surveytool.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;
import edu.iu.surveytool.services.SurveyService;

public class SurveyControllerTestCase {

	private SurveyController surveyController;	
	
	private SurveyService surveyService;
	
	@Before
	public void setUp() throws Exception {
		
		surveyService = Mockito.mock(SurveyService.class);
		surveyController= new SurveyController();
		surveyController.setSurveyService(surveyService);
	}

	@Test
	public void testGetSurveys() {
		List<Survey> expectedSurveys = new ArrayList<Survey>();
		expectedSurveys.add(new Survey());
		expectedSurveys.add(new Survey());
		
		Mockito.when(surveyService.getSurveys()).thenReturn(expectedSurveys);
		
		List<Survey> surveys = surveyController.getSurveys();
		
		Mockito.verify(surveyService, Mockito.times(1)).getSurveys();
		
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
		
		Mockito.when(surveyService.getQuestionsInSurvey(survey_id)).thenReturn(expectedQuestions);
		
		List<Question> questions = surveyController.getQuestionsInSurvey(survey_id);
		
		Mockito.verify(surveyService, Mockito.times(1)).getQuestionsInSurvey(argumentCaptor.capture());
		
		Assert.assertEquals(survey_id, argumentCaptor.getValue().intValue());
		Assert.assertNotNull(questions);
		Assert.assertEquals(expectedQuestions.size(), questions.size());
		Assert.assertEquals(expectedQuestions, questions);
	}

}

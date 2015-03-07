package edu.iu.surveytool.services.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.stereotype.Controller;

import edu.iu.surveytool.daos.ResponseDAO;
import edu.iu.surveytool.model.QuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseSubmitModel;
import edu.iu.surveytool.model.basic.Response;

public class ResponseServiceImplTestCase {
	
	private static final int VALID_SURVEY_ID =1;
	
	private static final int VALID_QUESTION_ID=2;
	
	private ResponseServiceImpl service;
	
	private ResponseDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(ResponseDAO.class);
		service = new ResponseServiceImpl();
		service.setResponseDAO(dao);
	}

	@Test
	public void testSeeResponsesForValidRequest() {
		
		ArgumentCaptor<SurveyQuestionResponseModel> argumentCaptor = ArgumentCaptor.forClass(SurveyQuestionResponseModel.class);	

		SurveyQuestionResponseModel requestModel = getValidRequest();
		SurveyQuestionResponseModel expectedSurveyQuestionResponseModel = getValidRequest();
		
		Mockito.when(dao.seeResponses(requestModel)).thenReturn(expectedSurveyQuestionResponseModel);
		
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(requestModel);
		
		Mockito.verify(dao, Mockito.times(1)).seeResponses(argumentCaptor.capture());
		
		SurveyQuestionResponseModel passedModel = argumentCaptor.getValue();
		
		Assert.assertNotNull(passedModel);
		Assert.assertEquals(requestModel, passedModel);
		
		Assert.assertNotNull(actualSurveyQuestionResponseModel);
		Assert.assertEquals(expectedSurveyQuestionResponseModel, actualSurveyQuestionResponseModel);
	}
	
	@Test
	public void testSeeResponsesForNullRequest(){
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(null);
		
		testInvalidModel(actualSurveyQuestionResponseModel);
	}
	
	@Test
	public void testSeeResponsesForNullQuestionModel(){
		SurveyQuestionResponseModel model = new SurveyQuestionResponseModel();
		model.setQuestionResponse(null);
		model.setSurvey_id(VALID_SURVEY_ID);
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(model);
		
		testInvalidModel(actualSurveyQuestionResponseModel);
	}
	
	@Test
	public void testSeeResponsesForZeroSurveyId(){
		SurveyQuestionResponseModel model = getValidRequest();
		model.setSurvey_id(0);
		
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(model);
		
		testInvalidModel(actualSurveyQuestionResponseModel);
		
	}
	
	@Test
	public void testSeeResponsesForNegativeSurveyId(){
		SurveyQuestionResponseModel model = getValidRequest();
		model.setSurvey_id(-1);
		
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(model);
		
		testInvalidModel(actualSurveyQuestionResponseModel);
		
	}
	
	@Test
	public void testSeeResponsesForZeroQuestionId(){
		SurveyQuestionResponseModel model = getValidRequest();
		model.getQuestionResponse().setQuestion_id(0);
		
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(model);
		
		testInvalidModel(actualSurveyQuestionResponseModel);
		
	}
	
	@Test
	public void testSeeResponsesForNegativeQuestionId(){
		SurveyQuestionResponseModel model = getValidRequest();
		model.getQuestionResponse().setQuestion_id(-1);
		
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel = service.seeResponses(model);
		
		testInvalidModel(actualSurveyQuestionResponseModel);
		
	}
	
	
	
	private void testInvalidModel(SurveyQuestionResponseModel model){
		Mockito.verifyZeroInteractions(dao);
		Assert.assertNull(model);
	}
	
	private SurveyQuestionResponseModel getValidRequest(){
		SurveyQuestionResponseModel model = new SurveyQuestionResponseModel();
		model.setSurvey_id(VALID_SURVEY_ID);
		
		QuestionResponseModel questionResponseModel = new QuestionResponseModel();
		questionResponseModel.setQuestion_id(VALID_QUESTION_ID);
		
		model.setQuestionResponse(questionResponseModel);
		
		return model;
	}
	
	private SurveyQuestionResponseModel getValidSubmitResponseRequest(){
		
		SurveyQuestionResponseModel model = getValidRequest();
		
		List<Response> responseList = new ArrayList<Response>();
		Response response1 = new Response();
		response1.setResponse("Response1");
		responseList.add(response1);
		
		
		model.getQuestionResponse().setResponses(responseList);
		return model;
	}
	
	private SurveyQuestionResponseSubmitModel getValidSurveyResponseRequestSubmitRequest(){		
		QuestionResponseModel questionResponseModel = new QuestionResponseModel();
		questionResponseModel.setQuestion_id(VALID_QUESTION_ID);
		
		List<Response> responseList = new ArrayList<Response>();
		Response response1 = new Response();
		response1.setResponse("Response1");
		responseList.add(response1);
		
		questionResponseModel.setResponses(responseList);
		
		List<QuestionResponseModel> questionResponseModels = new ArrayList<QuestionResponseModel>();
		questionResponseModels.add(questionResponseModel);
		
		SurveyQuestionResponseSubmitModel surveyQuestionResponseSubmitModel = new SurveyQuestionResponseSubmitModel();
		surveyQuestionResponseSubmitModel.setSurveyId(VALID_SURVEY_ID);
		surveyQuestionResponseSubmitModel.setQuestionResponses(questionResponseModels);
		
		return surveyQuestionResponseSubmitModel;
	}

	@Test	
	public void testSubmitResponseforValidRequest() {
		
		ArgumentCaptor<SurveyQuestionResponseModel> argumentCaptor = ArgumentCaptor.forClass(SurveyQuestionResponseModel.class);	

		SurveyQuestionResponseSubmitModel surveyQuestionResponseSubmitModel = getValidSurveyResponseRequestSubmitRequest();
		
		SurveyQuestionResponseModel surveyQuestionResponseModel = getValidSubmitResponseRequest();
		Mockito.when(dao.submitResponse(Matchers.any(SurveyQuestionResponseModel.class))).thenReturn(surveyQuestionResponseModel);
		
		SurveyQuestionResponseSubmitModel actualSurveyQuestionResponseSubmitModel= service.submitResponse(surveyQuestionResponseSubmitModel);
		
	}
	
	
}

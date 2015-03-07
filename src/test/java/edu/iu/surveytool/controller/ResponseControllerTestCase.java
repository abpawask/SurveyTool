package edu.iu.surveytool.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.iu.surveytool.model.QuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseSubmitModel;
import edu.iu.surveytool.model.basic.Response;
import edu.iu.surveytool.model.web.QuestionResponseSubmitModel;
import edu.iu.surveytool.services.ResponseService;

public class ResponseControllerTestCase {

	private ResponseController controller;
	
	private ResponseService service;
	
	private static final String VALID_RESPONSE="Response1";
	
	private static final int VALID_SURVEY_ID =1;
	
	private static final int VALID_QUESTION_ID=2;
	@Before
	public void setUp() throws Exception {
		
		service= Mockito.mock(ResponseService.class);
		
		controller = new ResponseController();
		controller.setResponseService(service);
	}
	
	private SurveyQuestionResponseModel getExpectedSurveyQuestionResponseModel(){
		
		SurveyQuestionResponseModel retval = new SurveyQuestionResponseModel();
		retval.setSurvey_id(VALID_SURVEY_ID);
		 
		QuestionResponseModel questionResponseModel = new QuestionResponseModel();
		questionResponseModel.setQuestion_id(VALID_QUESTION_ID);
		
		Response response1 = new Response();
		Response response2 = new Response();
		
		List<Response> responseList = new ArrayList<Response>();
		responseList.add(response1);
		responseList.add(response2);
		
		questionResponseModel.setResponses(responseList);
		
		retval.setQuestionResponse(questionResponseModel);
		return retval;
	}
	
	private SurveyQuestionResponseSubmitModel getExpectedSurveyQuestionResponseSubmitModel(){
		
		SurveyQuestionResponseSubmitModel surveyQuestionResponseSubmitModel = new SurveyQuestionResponseSubmitModel();
		return surveyQuestionResponseSubmitModel;
	}

	@Test
	public void testShowResponses() {
		ArgumentCaptor<SurveyQuestionResponseModel> argumentCaptor = ArgumentCaptor.forClass(SurveyQuestionResponseModel.class);	
		SurveyQuestionResponseModel expectedSurveyQuestionResponseModel = getExpectedSurveyQuestionResponseModel();
		Mockito.when(service.seeResponses(Matchers.any(SurveyQuestionResponseModel.class))).thenReturn(expectedSurveyQuestionResponseModel);
		
		SurveyQuestionResponseModel actualSurveyQuestionResponseModel=controller.showResponses(VALID_SURVEY_ID, VALID_QUESTION_ID);
		
		Mockito.verify(service, Mockito.times(1)).seeResponses(argumentCaptor.capture());
		
		SurveyQuestionResponseModel passedSurveyQuestionResponseModel = argumentCaptor.getValue();
		
		Assert.assertEquals(VALID_SURVEY_ID, passedSurveyQuestionResponseModel.getSurvey_id());
		Assert.assertNotNull(passedSurveyQuestionResponseModel.getQuestionResponse());
		Assert.assertEquals(VALID_QUESTION_ID, passedSurveyQuestionResponseModel.getQuestionResponse().getQuestion_id());
		
		Assert.assertNotNull(actualSurveyQuestionResponseModel);
		Assert.assertEquals(expectedSurveyQuestionResponseModel, actualSurveyQuestionResponseModel);
	}

	@Test
	public void testSubmitResponsesForValidResponse() {
		ArgumentCaptor<SurveyQuestionResponseModel> argumentCaptor = ArgumentCaptor.forClass(SurveyQuestionResponseModel.class);	
		SurveyQuestionResponseSubmitModel expectedSurveyQuestionResponseModel = getExpectedSurveyQuestionResponseSubmitModel();
		
		QuestionResponseSubmitModel validQuestionResponseSubmitModel = getValidQuestionResponseSubmitModel();
		
		Mockito.when(service.submitResponse(Matchers.any(SurveyQuestionResponseSubmitModel.class))).thenReturn(expectedSurveyQuestionResponseModel);
		
		ResponseEntity<SurveyQuestionResponseSubmitModel> response = controller.submitResponses(VALID_SURVEY_ID,validQuestionResponseSubmitModel);
		
		Assert.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		SurveyQuestionResponseSubmitModel actualSurveyQuestionResponseModel = response.getBody();
		
		Assert.assertNotNull(actualSurveyQuestionResponseModel);
		Assert.assertEquals(expectedSurveyQuestionResponseModel, actualSurveyQuestionResponseModel);
	}
	
	private QuestionResponseSubmitModel getValidQuestionResponseSubmitModel(){
		QuestionResponseSubmitModel questionResponseSubmitModel = new QuestionResponseSubmitModel();
		
		Response response11 = new Response();
		response11.setResponse(VALID_RESPONSE);		
		
		List<Response> response1 = new ArrayList<Response>();
		response1.add(response11);		
		
		
		QuestionResponseModel questionResponse1 = new QuestionResponseModel();
		questionResponse1.setQuestion_id(1);
		questionResponse1.setResponses(response1);
		
		List<QuestionResponseModel> questionResponseModelList = new ArrayList<QuestionResponseModel>();
		questionResponseModelList.add(questionResponse1);
		
		questionResponseSubmitModel.setQuestionResponses(questionResponseModelList);
		
		return questionResponseSubmitModel;
	}
	
	@Test
	public void testSubmitResponsesForInValidResponse(){
		Mockito.when(service.submitResponse( Matchers.any(SurveyQuestionResponseSubmitModel.class))).thenReturn(null);
		int survey_id =1;
		
		QuestionResponseSubmitModel questionResponseModel = new QuestionResponseSubmitModel();
		ResponseEntity<SurveyQuestionResponseSubmitModel> response = controller.submitResponses(survey_id,questionResponseModel);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertNull(response.getBody());
		
	}

}

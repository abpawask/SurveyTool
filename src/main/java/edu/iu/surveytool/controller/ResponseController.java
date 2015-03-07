package edu.iu.surveytool.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;










import edu.iu.surveytool.model.QuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseSubmitModel;
import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Response;
import edu.iu.surveytool.model.web.QuestionResponseSubmitModel;
import edu.iu.surveytool.services.ResponseService;

@RestController
public class ResponseController {
	
	@Autowired
	private ResponseService responseService;
	
	@RequestMapping(value="/surveyQuestionResponses/{survey_id}/{question_id}", method = RequestMethod.GET)
	public SurveyQuestionResponseModel showResponses(@PathVariable("survey_id") int survey_id, @PathVariable("question_id") int question_id){
		SurveyQuestionResponseModel surveyQuestionResponseModel = new SurveyQuestionResponseModel();
		
		surveyQuestionResponseModel.setSurvey_id(survey_id);
		QuestionResponseModel questionResponseModel = new QuestionResponseModel();
		questionResponseModel.setQuestion_id(question_id);
		
		surveyQuestionResponseModel.setQuestionResponse(questionResponseModel);
		
		return responseService.seeResponses(surveyQuestionResponseModel);
	}
		
	@RequestMapping(value="/surveyQuestionResponses/{survey_id}", method = RequestMethod.POST)
	public ResponseEntity<SurveyQuestionResponseSubmitModel> submitResponses(@PathVariable("survey_id") int survey_id, @RequestBody QuestionResponseSubmitModel questionResponseSubmitModel){
		ResponseEntity<SurveyQuestionResponseSubmitModel> retval;
		
		SurveyQuestionResponseSubmitModel submitModel = new SurveyQuestionResponseSubmitModel();
		submitModel.setSurveyId(survey_id);
		submitModel.setQuestionResponses(questionResponseSubmitModel.getQuestionResponses());
		
		SurveyQuestionResponseSubmitModel retvalSurveyQuestionResponseModel = responseService.submitResponse(submitModel);
		
		if(retvalSurveyQuestionResponseModel != null){
			retval=  new ResponseEntity<SurveyQuestionResponseSubmitModel>(retvalSurveyQuestionResponseModel, HttpStatus.ACCEPTED);
		}
		else{
			retval = new ResponseEntity<SurveyQuestionResponseSubmitModel>(HttpStatus.BAD_REQUEST);
		}
		return retval;
	}

	public void setResponseService(ResponseService responseService) {
		this.responseService = responseService;
	}
}

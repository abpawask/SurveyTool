package edu.iu.surveytool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;
import edu.iu.surveytool.services.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@RequestMapping(value="/surveys", method = RequestMethod.GET)
	public @ResponseBody List<Survey> getSurveys(){
		return surveyService.getSurveys();
	}
	
	@RequestMapping(value="/questions", method = RequestMethod.GET)
	public @ResponseBody List<Question> getQuestionsInSurvey(@RequestParam(value="survey_id") int surveyId){
		return surveyService.getQuestionsInSurvey(surveyId);
	}
}

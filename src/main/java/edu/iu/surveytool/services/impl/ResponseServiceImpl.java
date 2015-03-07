package edu.iu.surveytool.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iu.surveytool.daos.ResponseDAO;
import edu.iu.surveytool.model.QuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseSubmitModel;
import edu.iu.surveytool.model.basic.Response;
import edu.iu.surveytool.services.ResponseService;

@Service
public class ResponseServiceImpl implements ResponseService {

	@Autowired
	private ResponseDAO responseDAO;
	
	private boolean validateSurveyQuestionResponseModel(SurveyQuestionResponseModel model){
		boolean isValid = false;
		
		if(model != null && model.getQuestionResponse()!= null && model.getSurvey_id() >0 && model.getQuestionResponse().getQuestion_id()>0){
			isValid = true;
		}
		
		return isValid;
	}

	public SurveyQuestionResponseModel seeResponses(
			SurveyQuestionResponseModel surveyQuestionResponseModel) {
		SurveyQuestionResponseModel retval = null;
		if(validateSurveyQuestionResponseModel(surveyQuestionResponseModel)){
			retval = responseDAO.seeResponses(surveyQuestionResponseModel);
		}
		return retval;
	}

	private boolean validateSurveyQuestionResponseSubmitModel(SurveyQuestionResponseSubmitModel surveyQuestionResponseSubmitModel){
		
		
		return false;
	}
	
	@Transactional
	public SurveyQuestionResponseSubmitModel submitResponse(
			SurveyQuestionResponseSubmitModel surveyQuestionResponseSubmitModel) {
		
		SurveyQuestionResponseSubmitModel retval = null;
		List<QuestionResponseModel> questionResponseModel = new ArrayList<QuestionResponseModel>();
		boolean isSubmitValid = true;
		
		int survey_id = surveyQuestionResponseSubmitModel.getSurveyId();
		
		for(QuestionResponseModel currentQuestionResponseModel: surveyQuestionResponseSubmitModel.getQuestionResponses()){
			SurveyQuestionResponseModel surveyQuestionResponseModel = new SurveyQuestionResponseModel();
			surveyQuestionResponseModel.setSurvey_id(survey_id);
			surveyQuestionResponseModel.setQuestionResponse(currentQuestionResponseModel);
			
			if(validateSurveyQuestionResponseSubmitModel(surveyQuestionResponseModel)){
				SurveyQuestionResponseModel retvalSurveyQuestionResponseModel = responseDAO.submitResponse(surveyQuestionResponseModel);
				if(retvalSurveyQuestionResponseModel!= null && retvalSurveyQuestionResponseModel.getQuestionResponse()!=null){
					questionResponseModel.add(retvalSurveyQuestionResponseModel.getQuestionResponse());
				}
				else{
					isSubmitValid = false;
					break;
				}				
			}
			else{
				isSubmitValid = false;
				break;
			}			
		}
		
		if(questionResponseModel.size() >0 && isSubmitValid){
			retval = new SurveyQuestionResponseSubmitModel();
			retval.setSurveyId(survey_id);
			retval.setQuestionResponses(questionResponseModel);
		}		
		return retval;
	}
	
	private boolean validateSurveyQuestionResponseSubmitModel(SurveyQuestionResponseModel surveyQuestionResponseModel){
		
		boolean isValid = false;
		
		if(validateSurveyQuestionResponseModel(surveyQuestionResponseModel)){
			List<Response> responses = surveyQuestionResponseModel.getQuestionResponse().getResponses();
			
			if(responses!= null && responses.size()>0){
				isValid = true;
			}
		}
		
		return isValid;
	}


	public void setResponseDAO(ResponseDAO responseDAO) {
		this.responseDAO = responseDAO;
	}

}

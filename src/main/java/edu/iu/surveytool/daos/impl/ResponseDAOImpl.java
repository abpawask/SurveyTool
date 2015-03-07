package edu.iu.surveytool.daos.impl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.iu.surveytool.daos.BaseDAO;
import edu.iu.surveytool.daos.ResponseDAO;
import edu.iu.surveytool.model.QuestionResponseModel;
import edu.iu.surveytool.model.SurveyQuestionResponseModel;
import edu.iu.surveytool.model.basic.Response;

@Repository
public class ResponseDAOImpl extends BaseDAO implements ResponseDAO {
	
	private static final String SAVE_RESPONSE_QUERY ="insert into response(survey_id, question_id, text) values (?,?,?)";
	
	private static final String GET_RESPONSES_QUERY="select * from response where survey_id=? and question_id=?";
	
	private static final RowMapper<Response> RESPONSE_ROW_MAPPER = new RowMapper<Response>() {

		public Response mapRow(ResultSet rs, int arg1) throws SQLException {
			Response response = new Response();
			
			response.setId(rs.getInt("response_id"));
			response.setResponse(rs.getString("text"));
			return response;
		}
		
	};

	@Autowired
	public ResponseDAOImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		
	}
	

	public SurveyQuestionResponseModel seeResponses(
			SurveyQuestionResponseModel surveyQuestionResponseModel) {
		
		SurveyQuestionResponseModel retval = new SurveyQuestionResponseModel();
		
		int survey_id = surveyQuestionResponseModel.getSurvey_id();
		
		QuestionResponseModel questionResponseModel = surveyQuestionResponseModel.getQuestionResponse();
		int question_id = questionResponseModel.getQuestion_id();
		
		List<Response> responseList = jdbcTemplate.query(GET_RESPONSES_QUERY, RESPONSE_ROW_MAPPER, survey_id, question_id);
		
		QuestionResponseModel questionResponseModelRetval = new QuestionResponseModel();
		questionResponseModelRetval.setQuestion_id(question_id);
		questionResponseModelRetval.setResponses(responseList);
		
		retval.setSurvey_id(survey_id);
		retval.setQuestionResponse(questionResponseModelRetval);
		
		return retval;
	}

	public SurveyQuestionResponseModel submitResponse(
			SurveyQuestionResponseModel surveyQuestionResponseModel) {
		// TODO Auto-generated method stub
		int survey_id = surveyQuestionResponseModel.getSurvey_id();
		
		QuestionResponseModel questionResponseModel = surveyQuestionResponseModel.getQuestionResponse();
		int question_id = questionResponseModel.getQuestion_id();
		
		List<Response> responses = questionResponseModel.getResponses();
		
		for(Response response:responses){
			jdbcTemplate.update(SAVE_RESPONSE_QUERY, survey_id, question_id, response.getResponse());
		}
		
		return surveyQuestionResponseModel;
	}

}

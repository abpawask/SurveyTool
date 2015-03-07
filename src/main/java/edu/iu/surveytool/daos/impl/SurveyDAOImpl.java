package edu.iu.surveytool.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.iu.surveytool.daos.BaseDAO;
import edu.iu.surveytool.daos.SurveyDAO;
import edu.iu.surveytool.model.basic.Question;
import edu.iu.surveytool.model.basic.Survey;

@Repository
public class SurveyDAOImpl extends BaseDAO implements SurveyDAO {

	private final static String GET_SURVEYS_QUERY="select * from survey";
	
	private final static String GET_QUESTIONS_QUERY= "select * from question where survey_id=?";
			
	
	private final static RowMapper<Survey> GET_SURVEYS_ROW_MAPPER = new RowMapper<Survey>(){

		public Survey mapRow(ResultSet rs, int arg1) throws SQLException {
			Survey survey = new Survey();
			
			survey.setId(rs.getInt("survey_id"));
			survey.setName(rs.getString("survey_name"));
			return survey;
		}
		
	};
	
	private final static RowMapper<Question> QUESTION_SURVEY_MAPPER = new RowMapper<Question>() {

		public Question mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return null;
		}
	};
	
	@Autowired
	public SurveyDAOImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		
	}

	public List<Survey> getSurveys() {
		
		List<Survey> surveyList=jdbcTemplate.query(GET_SURVEYS_QUERY, GET_SURVEYS_ROW_MAPPER);
		
		return surveyList;
	}

	public List<Question> getQuestionsInSurvey(int surveyId) {
		
		List<Question> questionList = jdbcTemplate.query(GET_QUESTIONS_QUERY, QUESTION_SURVEY_MAPPER, surveyId);
		
		return questionList;
	}

}

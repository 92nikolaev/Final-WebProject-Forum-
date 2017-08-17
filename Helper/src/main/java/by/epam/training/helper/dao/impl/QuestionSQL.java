package by.epam.training.helper.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.constant.ColumnNameDB;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.SQLCommand;
import by.epam.training.helper.dao.QuestionDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.ReleaseConnection;

public class QuestionSQL implements QuestionDAO {
	private static final Logger logger = LogManager.getLogger(QuestionSQL.class);
	@Override
	public ArrayList<Question> getQuestionsWithLimit(int offset, int itemOnPage) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		ArrayList<Question> questions = new ArrayList<>();
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_QUESTIONS_WITH_LIMIT)){
					preparedStatement.setInt(1, offset);
					preparedStatement.setInt(2, itemOnPage);
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						while(resultSet.next()){
							Question question = new Question();
							question.setId(resultSet.getInt(ColumnNameDB.QUESTION_ID));
							question.setTitle(resultSet.getString(ColumnNameDB.QUESTION_TITLE));
							question.setContent(resultSet.getString(ColumnNameDB.QUESTION_TEXT));
							question.setUserLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
							question.setAnswerCount(resultSet.getInt(ColumnNameDB.QUESTION_COUNT_ANSWER));
							questions.add(question);
						}
					} 
			} catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_GET_QUESTIONS, e);
				throw new DAOException(ErrorMessage.ERROR_GET_QUESTIONS);
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return questions;
	}
	
	@Override
	public ArrayList<Question> getSearchQuestionWithLimit(int offset, int itemOnPage, String searchQuestion)throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		ArrayList<Question> questions = new ArrayList<>();
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_QUESTIONS_SEARCH_WITH_LIMIT)){
					preparedStatement.setString(1, searchQuestion);
					preparedStatement.setString(2, searchQuestion);
					preparedStatement.setInt(3, offset);
					preparedStatement.setInt(4, itemOnPage);
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						while(resultSet.next()){
							Question question = new Question();
							question.setId(resultSet.getInt(ColumnNameDB.QUESTION_ID));
							question.setTitle(resultSet.getString(ColumnNameDB.QUESTION_TITLE));
							question.setContent(resultSet.getString(ColumnNameDB.QUESTION_TEXT));
							question.setUserLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
							question.setAnswerCount(resultSet.getInt(ColumnNameDB.QUESTION_COUNT_ANSWER));
							questions.add(question);
						}
					} 
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(ErrorMessage.ERROR_GET_SEARCH_QUESTIONS, e);
				throw new DAOException(ErrorMessage.ERROR_GET_SEARCH_QUESTIONS);					
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return questions;
	}

	@Override
	public int getAmountQuestions() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		int amount = 0;
		try {
			connection = connectionPool.take();
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(SQLCommand.SELECT_QUESTION_AMOUNT)){
					if(resultSet.next()){
						amount = resultSet.getInt(ColumnNameDB.QUESTION_AMOUNT);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(ErrorMessage.ERROR_GET_COUNT_QUESTIONS, e);
				throw new DAOException(ErrorMessage.ERROR_GET_COUNT_QUESTIONS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return amount;
	}
	@Override
	public int getAmountSearchQuestion(String searchQuestion) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		int amount = 0;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_QUESTIONS_COUNT_SEARCH)){
				preparedStatement.setString(1, searchQuestion);
				preparedStatement.setString(2, searchQuestion);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()){
						amount = resultSet.getInt(ColumnNameDB.QUESTION_COUNT_SEARCH);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(ErrorMessage.ERROR_GET_COUNT_QUESTIONS, e);
				throw new DAOException(ErrorMessage.ERROR_GET_COUNT_QUESTIONS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return amount;
	}

	@Override
	public ArrayList<Question> getUserQuestion(int user_id) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		ArrayList<Question> questions = new ArrayList<>();
		try{
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_TEN_LAST_QUESTION_USER)){
				preparedStatement.setInt(1, user_id);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()){
						Question question = new Question();
						question.setId(resultSet.getInt(ColumnNameDB.QUESTION_ID));
						question.setTitle(resultSet.getString(ColumnNameDB.QUESTION_TITLE));
						question.setDateCreated(resultSet.getDate(ColumnNameDB.QUESTION_CREATED));
						questions.add(question);
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error(ErrorMessage.ERROR_GET_QUESTIONS, e);
			throw new DAOException(ErrorMessage.ERROR_GET_QUESTIONS);
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return questions;
	}

	@Override
	public void addQuestion(int user_id, String title, String content) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.INSERT_QUESTION);){
				preparedStatement.setInt(1, user_id);
				preparedStatement.setString(2, title);
				preparedStatement.setString(3, content);
				preparedStatement.executeUpdate();
			}catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_SAVE_QUESTION, e);
				throw new DAOException(ErrorMessage.ERROR_SAVE_QUESTION);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION + e);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}	
	}

	@Override
	public Question getQestionById(int questionId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Question question = null;
		
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_QUESTION_BY_ID)){
				preparedStatement.setInt(1, questionId);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						question = new Question();
						question.setId(resultSet.getInt(ColumnNameDB.QUESTION_ID));
						question.setUserLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
						question.setTitle(resultSet.getString(ColumnNameDB.QUESTION_TITLE));
						question.setContent(resultSet.getString(ColumnNameDB.QUESTION_TEXT));
						question.setDateCreated(resultSet.getDate(ColumnNameDB.QUESTION_CREATED));
						question.setAnswerCount(resultSet.getInt(ColumnNameDB.QUESTION_COUNT_ANSWER));	
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_GET_QUESTIONS, e);
				throw new DAOException(ErrorMessage.ERROR_GET_QUESTIONS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return question;
	}

	@Override
	public void updateQuestionById(int questionId, String questionTitle, String questionContent) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_QUESTION);){
				preparedStatement.setString(1, questionTitle);
				preparedStatement.setString(2, questionContent);
				preparedStatement.setInt(3, questionId);
				preparedStatement.executeUpdate();
			}catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_UPDATE_QUESTION, e);
				throw new DAOException(ErrorMessage.ERROR_UPDATE_QUESTION);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION + e);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}	
		
	}
}

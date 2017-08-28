package by.epam.training.helper.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.constant.ColumnNameDB;
import by.epam.training.helper.constant.ErrorMessageDAO;
import by.epam.training.helper.constant.SQLCommand;
import by.epam.training.helper.dao.AnswerDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.ReleaseConnection;

public class AnswerSQL implements AnswerDAO {
	private static final Logger logger = LogManager.getLogger(AnswerSQL.class);
	@Override
	public ArrayList<Answer> getAnswerWithLimit(int questionId, int offset, int itemOnPage) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		ArrayList<Answer> answers = new ArrayList<>();
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_ANSWER_WITH_LIMIT_BY_QESTION_ID)){
				preparedStatement.setInt(1, questionId);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, itemOnPage);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()){
						Answer answer = new Answer();
						answer.setId(resultSet.getInt(ColumnNameDB.ANSWER_ID));
						answer.setUserLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
						answer.setDateCreated(resultSet.getDate(ColumnNameDB.ANSWER_CREATED));
						answer.setAverageMark(resultSet.getInt(ColumnNameDB.ANSWER_AVERAGE_MARK));
						answer.setContent(resultSet.getString(ColumnNameDB.ANSWER_TEXT));
						answers.add(answer);
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_ANSWER, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_ANSWER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return answers;
	}

	@Override
	public int getAmountAnswers(int questionId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		int amount = 0;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_ANSWER_AMOUNT_BY_QUESTION_ID)){
				preparedStatement.setInt(1, questionId);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						amount = resultSet.getInt(ColumnNameDB.ANSWER_COUNT);
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_COUNT_ANSWERS, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_COUNT_ANSWERS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return amount;
	}

	@Override
	public void addAnswer(Answer answer) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.INSERT_ANSWER)){
				preparedStatement.setInt(1, answer.getUserId());
				preparedStatement.setInt(2, answer.getQuestionId());
				preparedStatement.setString(3, answer.getContent());
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_ADD_ANSWER, e);
				throw new DAOException(ErrorMessageDAO.ERROR_ADD_ANSWER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
	}

	@Override
	public Answer answerById(int answerId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Answer answer = new Answer();
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_ANSWER_BY_ID)){
				preparedStatement.setInt(1, answerId);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						answer.setId(resultSet.getInt(ColumnNameDB.ANSWER_ID));
						answer.setUserId(resultSet.getInt(ColumnNameDB.USER_ID));
						answer.setQuestionId(resultSet.getInt(ColumnNameDB.QUESTION_ID));
						answer.setContent(resultSet.getString(ColumnNameDB.ANSWER_TEXT));
						answer.setDateCreated(resultSet.getDate(ColumnNameDB.ANSWER_CREATED));
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_ANSWER_BY_ID, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_ANSWER_BY_ID);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return answer;
	}

	@Override
	public void updateAnswerById(int answerId, String answerContent) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_ANSWER)){
				preparedStatement.setString(1, answerContent);
				preparedStatement.setInt(2, answerId);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_UPDATE_ANSWER, e);
				throw new DAOException(ErrorMessageDAO.ERROR_UPDATE_ANSWER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
	}
}

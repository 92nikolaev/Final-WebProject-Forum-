package by.epam.training.helper.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.SQLCommand;
import by.epam.training.helper.dao.ValidationDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.ReleaseConnection;

public class ValidationSQL implements ValidationDAO {
	private static final Logger logger = LogManager.getLogger(ValidationSQL.class);
	@Override
	public String checkUserLoginEmailInDB(String login, String email) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		String answer = null;
		try {
			connection = connectionPool.take();
				try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_CHECK_USER_LOGIN_EMAIL)) {
					preparedStatement.setString(1, login);
					preparedStatement.setString(2, email);
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						if(resultSet.next()){
							answer = resultSet.getString(1);
						}
					}
				} catch (SQLException e) {
					logger.error(ErrorMessage.ERROR_CHECK_USER_EXISTS, e);
					throw new DAOException(ErrorMessage.ERROR_CHECK_USER_EXISTS + e);
				}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION , e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION  + e);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return answer;
	}
	@Override
	public String checkeExistQuestion(int questionId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		String answer = null;
		try {
			connection = connectionPool.take();
				try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_CHECK_QUESTION)) {
					preparedStatement.setInt(1, questionId);
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						if(resultSet.next()){
							answer = resultSet.getString(1);
						}
					}
				} catch (SQLException e) {
					logger.error(ErrorMessage.ERROR_QUESTION_NOT_EXISTS, e);
					throw new DAOException(ErrorMessage.ERROR_QUESTION_NOT_EXISTS + e);
				}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION , e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION  + e);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return answer;
	}

}

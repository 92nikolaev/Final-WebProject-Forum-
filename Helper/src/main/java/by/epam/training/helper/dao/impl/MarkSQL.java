package by.epam.training.helper.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Mark;
import by.epam.training.helper.constant.ColumnNameDB;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.SQLCommand;
import by.epam.training.helper.dao.MarkDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.ReleaseConnection;

public class MarkSQL implements MarkDAO {
	private static final Logger logger = LogManager.getLogger(MarkSQL.class);
	@Override
	public Mark getMarkByUserIdAnswerId(int userId, int answerId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Mark mark = new Mark();
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_MARK_BY_USER_ID_ANSWER_ID)){
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, answerId);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						mark.setId(resultSet.getInt(ColumnNameDB.MARK_ID));
						mark.setAnswerId(resultSet.getInt(ColumnNameDB.ANSWER_ID));
						mark.setUserId(resultSet.getInt(ColumnNameDB.USER_ID));
						mark.setMarkVolue(resultSet.getInt(ColumnNameDB.MARK_VALUE));
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_GET_ANSWER, e);
				throw new DAOException(ErrorMessage.ERROR_GET_ANSWER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return mark;
	}

	@Override
	public void addMark(int userId, int answerId, int mark) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.INSERT_MARK)){
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, answerId);
				preparedStatement.setInt(3, mark);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_INSER_MARK, e);
				throw new DAOException(ErrorMessage.ERROR_INSER_MARK);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		
	}

	@Override
	public void updateMark(int markId, int mark) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_MARK)){
				preparedStatement.setInt(1, mark);
				preparedStatement.setInt(2, markId);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				logger.error(ErrorMessage.ERROR_UPDATE_ANSWER, e);
				throw new DAOException(ErrorMessage.ERROR_GET_ANSWER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessage.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessage.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		
	}



}

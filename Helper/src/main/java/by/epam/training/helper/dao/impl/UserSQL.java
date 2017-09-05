package by.epam.training.helper.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.constant.ColumnNameDB;
import by.epam.training.helper.constant.ErrorMessageDAO;
import by.epam.training.helper.constant.SQLCommand;
import by.epam.training.helper.dao.UserDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.ReleaseConnection;


public class UserSQL implements UserDAO {
	private static final Logger logger = LogManager.getLogger(UserSQL.class);
	@Override
	public void signUp(User user) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.INSERT_SIGN_UP);){
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getSurname());
				preparedStatement.setString(3, user.getLogin());
				preparedStatement.setString(4, user.getEmail());
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.executeUpdate();
			}catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_SAVE_USER, e);
				throw new DAOException(ErrorMessageDAO.ERROR_SAVE_USER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}	
	}
	@Override
	public User signIn(String login, String hahsCode) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		User user = null;
		try{
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_SIGN_IN)){
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, hahsCode);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						user = new User();
						user.setId(resultSet.getInt(ColumnNameDB.USER_ID));
						user.setLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
						user.setRole(resultSet.getByte(ColumnNameDB.USER_ROLE));
						user.setStatus(resultSet.getByte(ColumnNameDB.USER_STATUS));
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_USER, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_USER);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return user;
	}
	@Override
	public ArrayList<User> getUsersWithLimit(int offset, int itemOnPage) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		ArrayList<User> users = new ArrayList<>();
			try {
				connection = connectionPool.take();
				try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_USERS_WITH_LIMIT)){
					preparedStatement.setInt(1, offset);
					preparedStatement.setInt(2, itemOnPage);
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						while(resultSet.next()){
							User user = new User();
							user.setId(resultSet.getInt(ColumnNameDB.USER_ID));
							user.setLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
							user.setRole(resultSet.getByte(ColumnNameDB.USER_ROLE));
							user.setDateCreated(resultSet.getDate(ColumnNameDB.USER_CREATED));
							user.setStatus(resultSet.getByte(ColumnNameDB.USER_STATUS));
							user.setAverageMark(resultSet.getDouble(ColumnNameDB.USER_AVERAGE_MARK));
							user.setCountMark(resultSet.getInt(ColumnNameDB.MARK_COUNT));
							user.setCountAnswer(resultSet.getInt(ColumnNameDB.ANSWER_COUNT));
							users.add(user);
						}
					}
				} catch (SQLException e) {
					logger.error(ErrorMessageDAO.ERROR_GET_USERS, e);
					throw new DAOException(ErrorMessageDAO.ERROR_GET_USERS);
				}
			} catch (ConnectionPoolException e) {
				logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
				throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
			}finally {
				ReleaseConnection.freeConnection(connection, connectionPool);
			}
		
		return users;
	}
	@Override
	public int getCountUser() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		int count = 0;
		try {
			connection = connectionPool.take();
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(SQLCommand.SELECT_USERS_AMOUNT)){
					if(resultSet.next()){
						count = resultSet.getInt(ColumnNameDB.COUNT_USERS);
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_COUNT_USERS, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_COUNT_USERS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return count;
	}
	@Override
	public User getUserById(int user_id) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		User user = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_USER_BY_ID)){
				preparedStatement.setInt(1, user_id);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						user = new User();
						user.setId(resultSet.getInt(ColumnNameDB.USER_ID));
						user.setName(resultSet.getString(ColumnNameDB.USER_NAME));
						user.setSurname(resultSet.getString(ColumnNameDB.USER_SURNAME));
						user.setLogin(resultSet.getString(ColumnNameDB.USER_LOGIN));
						user.setEmail(resultSet.getString(ColumnNameDB.USER_EMAIL));
						user.setDateCreated(resultSet.getDate(ColumnNameDB.USER_CREATED));
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_USER_BY_ID, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_USER_BY_ID);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return user;
	}
	@Override
	public String getPasswordById(int userId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		String answer = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_USER_PASSWORD_BY_USER_ID)) {
				preparedStatement.setInt(1, userId);
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.next()){
						answer = resultSet.getString(1);
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_CURRENT_PASSWORD, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_CURRENT_PASSWORD);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}
		return answer;
	}
	@Override
	public void updatePassword(int userId, String newPassword) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_USER_PASSWORD)){
				preparedStatement.setString(1, newPassword);
				preparedStatement.setInt(2, userId);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_UPDATE_PASSWORD, e);
				throw new DAOException(ErrorMessageDAO.ERROR_UPDATE_PASSWORD);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}	
		
	}
	@Override
	public void editUserField(int userId, String nameEdit, String surnameEdit, String emailEdit) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
			try {
				connection = connectionPool.take();
				try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_USER_PROFILE)){
					preparedStatement.setString(1, nameEdit);
					preparedStatement.setString(2, surnameEdit);
					preparedStatement.setString(3, emailEdit);
					preparedStatement.setInt(4, userId);
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					logger.error(ErrorMessageDAO.ERROR_UPDATE_USER, e);
					throw new DAOException(ErrorMessageDAO.ERROR_UPDATE_USER);
				}
			} catch (ConnectionPoolException e) {
				logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
				throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
			}finally {
				ReleaseConnection.freeConnection(connection, connectionPool);
			}	
		
	}
	@Override
	public void lockUnlockUser(int userId, byte status) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
			try {
				connection = connectionPool.take();
				try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_USER_STATUS)){
					preparedStatement.setByte(1, status);
					preparedStatement.setInt(2, userId);
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					logger.error(ErrorMessageDAO.ERROR_LOCK_OR_UNLOCK_USER, e);
					throw new DAOException(ErrorMessageDAO.ERROR_LOCK_OR_UNLOCK_USER);
				}
			} catch (ConnectionPoolException e) {
				logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
				throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
			}finally {
				ReleaseConnection.freeConnection(connection, connectionPool);
			}	
	}
	@Override
	public void assignModeratorOrUser(int userId, byte moderator_role) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
			try {
				connection = connectionPool.take();
				try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_USER_ROLE)){
					preparedStatement.setByte(1, moderator_role);
					preparedStatement.setInt(2, userId);
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					logger.error(ErrorMessageDAO.ERROR_CHANGE_ROLE, e);
					throw new DAOException(ErrorMessageDAO.ERROR_CHANGE_ROLE);
				}
			} catch (ConnectionPoolException e) {
				logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
				throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);
			}finally {
				ReleaseConnection.freeConnection(connection, connectionPool);
			}	
	}	
}

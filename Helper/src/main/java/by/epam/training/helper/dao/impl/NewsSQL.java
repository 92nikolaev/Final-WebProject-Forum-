package by.epam.training.helper.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.constant.ColumnNameDB;
import by.epam.training.helper.constant.ErrorMessageDAO;
import by.epam.training.helper.constant.SQLCommand;
import by.epam.training.helper.dao.NewsDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.ReleaseConnection;

public class NewsSQL implements NewsDAO {
	private static final Logger logger = LogManager.getLogger(NewsSQL.class);
	@Override
	public ArrayList<News> getNewsWithLimit(int offset, int newsOnPage) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		ArrayList<News> listnews = new ArrayList<>();
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.SELECT_NEWS_WITH_LIMIT)){
					preparedStatement.setInt(1, offset);
					preparedStatement.setInt(2, newsOnPage);
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						while(resultSet.next()){
							News news = new News();
							news.setId(resultSet.getInt(ColumnNameDB.NEWS_ID));
							news.setTitle(resultSet.getString(ColumnNameDB.NEWS_TITLE));
							news.setContent(resultSet.getString(ColumnNameDB.NEWS_CONTENT));
							listnews.add(news);
						}
					} 
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_NEWS, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_NEWS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return listnews;
	}
	@Override
	public void addNews(String titleNews, String contentNews) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = connectionPool.take();
			try(PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand.INSERT_NEWS)){				
				preparedStatement.setString(1, titleNews);
				preparedStatement.setString(2, contentNews);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_INSER_NEWS, e);
				throw new DAOException(ErrorMessageDAO.ERROR_INSER_NEWS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		
		
	}
	@Override
	public int getCountNews() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		int count = 0;
		try {
			connection = connectionPool.take();
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(SQLCommand.SELECT_NEWS_COUNT)){
					if(resultSet.next()){
						count = resultSet.getInt(ColumnNameDB.NEWS_COUNT);
					}
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_GET_COUNT_NEWS, e);
				throw new DAOException(ErrorMessageDAO.ERROR_GET_COUNT_NEWS);
			}
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
			throw new DAOException(ErrorMessageDAO.ERROR_CONNECTION);	
		}finally {
			ReleaseConnection.freeConnection(connection, connectionPool);
		}
		return count;
	}

}

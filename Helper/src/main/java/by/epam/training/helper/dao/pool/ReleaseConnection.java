package by.epam.training.helper.dao.pool;

import java.sql.Connection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.constant.ErrorMessageDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;

/**
 * To free a connection to the list of free connections
 * @author Nikolaev Ilya
 *
 */
public class ReleaseConnection {
	private static final Logger logger = LogManager.getLogger(ReleaseConnection.class);
	public static void freeConnection(Connection connection, ConnectionPool connectionPool) {
		if(connection != null){
			try {
				connectionPool.free(connection);
			} catch (InterruptedException | ConnectionPoolException e) {
				logger.error(ErrorMessageDAO.FAILD_RELEASE_CONNECTION + e);
			}	
		}
	}
}

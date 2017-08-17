package by.epam.training.helper.dao.pool;

import java.sql.Connection;

import by.epam.training.helper.dao.exception.ConnectionIsNullException;

/**
 * @author Nikolaev Ilya
 *
 */
public class ReleaseConnection {

	public static void freeConnection(Connection connection, ConnectionPool connectionPool) {
		if(connection != null){
			try {
				connectionPool.free(connection);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ConnectionIsNullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}

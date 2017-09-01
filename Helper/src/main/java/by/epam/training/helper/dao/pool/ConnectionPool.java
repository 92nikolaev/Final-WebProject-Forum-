/**
 * 
 */
package by.epam.training.helper.dao.pool;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import by.epam.training.helper.constant.ErrorMessageDAO;
import by.epam.training.helper.constant.ParameterDB;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
/**
 * A class that provides connections for user requests.
 * @author Nikolaev Ilya
 */
public class ConnectionPool implements Closeable {
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
	private static ConnectionPool instance;
	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;
	private BlockingQueue<Connection> freeConnection ;
	private BlockingQueue<Connection> busyConnection;
	private boolean flag = false;
	
	/**
	 * This constructor serves to initialize the connection parameters to the database. 
	 */
	private ConnectionPool() {
		ResourceManagerDB resourceManager = ResourceManagerDB.getInstance();
		this.driver = resourceManager.getValue(ParameterDB.DRIVER_DB);
		this.url = resourceManager.getValue(ParameterDB.URL_DB);
		this.user = resourceManager.getValue(ParameterDB.USER_DB);
		this.password = resourceManager.getValue(ParameterDB.PASSWORD_DB);
		this.poolsize = Integer.parseInt(resourceManager.getValue(ParameterDB.POOLSIZE_DB));
	}
	
	
	public static ConnectionPool getInstance() {
		if(instance == null){
			instance = new ConnectionPool();
		}
		return instance;
	}

	/**This method creates free connections.
	 * And also creates list busy connection.
	 * @throws ConnectionPoolException
	 */
	 void init() throws ConnectionPoolException {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);
		try {
			Class.forName(driver);
			for(int i = 0; i < poolsize; i++){
				Connection connection = DriverManager.getConnection(url, user, password);
				freeConnection.add(connection);
			}
			flag = true;
		} catch (ClassNotFoundException e) {
			logger.error(ErrorMessageDAO.ERROR_DB_DRIVER + e);
			throw new ConnectionPoolException(ErrorMessageDAO.ERROR_DB_DRIVER);
		} catch (SQLException e) {
			logger.error(ErrorMessageDAO.ERROR_SQL + e);
			throw new ConnectionPoolException(ErrorMessageDAO.ERROR_SQL);
		}
	}
	 
	 /** The method provides a free connection.
	  * @return {@link Connection}
	  * @throws ConnectionPoolException
	  */
	public Connection take() throws ConnectionPoolException{
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTING + e);
			throw new ConnectionPoolException(ErrorMessageDAO.ERROR_CONNECTING);
		}
		return connection;	
	}
	
	/**Method of transferring a connection from a statute busy status is free.
	 * @param connection
	 * @throws InterruptedException
	 * @throws @throws ConnectionPoolException
	 */
	public void free(Connection connection) throws InterruptedException, ConnectionPoolException{
		if (connection == null) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION_EMPTY);
			throw new ConnectionPoolException(ErrorMessageDAO.ERROR_CONNECTION_EMPTY);
		}
		Connection temporaryConnection = connection;
		connection = null;
		busyConnection.remove(temporaryConnection);
		freeConnection.put(temporaryConnection);
	}
	public boolean checkInit() {
		return flag;
	}
	/**
	 * Method for close all connection
	 */
	@Override
	public void close() throws IOException {
		 List<Connection> connections = new ArrayList<>();
		 connections.addAll(busyConnection);
		 connections.addAll(freeConnection);
		 
		 for (Connection connection : connections) {
			try {
				if(connection.isClosed()== false){
					connection.close();
				}
			} catch (SQLException e) {
				logger.error(ErrorMessageDAO.ERROR_SQL_CLOSE + e);
			}
		}		
	}
}

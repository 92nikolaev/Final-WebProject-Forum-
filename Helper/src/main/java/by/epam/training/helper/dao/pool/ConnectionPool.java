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

import by.epam.training.helper.constant.ParameterDB;
import by.epam.training.helper.dao.exception.ConnectionIsNullException;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
/**
 * @author Nikolaev Ilya
 *
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
	
	/**init connection parametrs*/
	private ConnectionPool() {
		ResourceManagerBD resourceManager = ResourceManagerBD.getInstance();
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


	public void init() throws ConnectionPoolException {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);
		try {
			Class.forName(driver);
			for(int i = 0; i < poolsize; i++){
				Connection connection = DriverManager.getConnection(url, user, password);
				freeConnection.add(connection);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection take() throws ConnectionPoolException{
		Connection connection = null;
		
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return connection;	
	}
	public void free(Connection connection) throws InterruptedException, ConnectionIsNullException{
		if (connection == null) {
			logger.error("connection is empty");
			System.out.println("connection is empty method free");
			throw new ConnectionIsNullException();
		}
		Connection temporaryConnection = connection;
		connection = null;
		busyConnection.remove(temporaryConnection);
		freeConnection.put(temporaryConnection);
	}
	
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
				e.printStackTrace();
			}
		}
		
	}

}

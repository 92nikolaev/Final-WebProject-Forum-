package by.epam.training.helper.dao.pool;

import java.io.IOException;

import by.epam.training.helper.dao.exception.ConnectionPoolException;
/**
 * Connection pool initializing class
 * @author Nikolaev Ilya
 *
 */
public class InitializationConnectionPool {
	private static InitializationConnectionPool inctance;
	private InitializationConnectionPool(){}
	
	public static InitializationConnectionPool getInctance() {
		if(inctance == null){
			inctance = new InitializationConnectionPool();
		}
		return inctance;
	}
	public void init() throws ConnectionPoolException{
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.init();
	}
	public void destroy()throws ConnectionPoolException, IOException{
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.close();
	}
}
package by.epam.training.helper.test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.pool.ConnectionPool;
import by.epam.training.helper.dao.pool.InitializationConnectionPool;


public class ConnectionPoolTest {

	@Before
	public void setUp() {
		InitializationConnectionPool initializationPool = InitializationConnectionPool.getInctance();
		try {
			initializationPool.init();
		} catch (ConnectionPoolException e) {
			fail("Failed init conection");
		}	
	}
	@Test
	public void test() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			assertNotNull(connection);
		} catch (ConnectionPoolException e) {
			fail("Failed get conection");
		}
		
	}
	@After
	public void tearDown() {
		InitializationConnectionPool initializationPool = InitializationConnectionPool.getInctance();
		try {
			initializationPool.destroy();
		}catch (Exception e) {
			fail("Failed close conection");
		}
	}

}

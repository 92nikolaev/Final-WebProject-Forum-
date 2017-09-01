package by.epam.training.helper.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.dao.UserDAO;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.dao.pool.InitializationConnectionPool;

public class SignInTest {
	private final String USER_LOGIN = "nikolaev92";
	private final String USER_PASSWORD = "629babe5cdc24b1a5978e361927b2ca2";

	@Before
	public void setUp() {
		InitializationConnectionPool initializationPool = InitializationConnectionPool.getInctance();
		try {
			initializationPool.init();
		} catch (ConnectionPoolException e) {
			fail("Failed close conection");
		}	
	}
	@Test
	public void test() {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			User user = userDAO.signIn(USER_LOGIN, USER_PASSWORD);
			assertNotNull(user);
		} catch (DAOException e) {
			fail("Failed with SQL or conection");
		}
		
	}
	@After
	public void tearDown() throws Exception {
		InitializationConnectionPool initializationPool = InitializationConnectionPool.getInctance();
		try {
			initializationPool.destroy();
		}catch (Exception e) {
			fail("Failed close conection");
		}
	}
	
}

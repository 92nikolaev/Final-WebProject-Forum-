package by.epam.training.helper.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import by.epam.training.helper.constant.ErrorMessageDAO;
import by.epam.training.helper.constant.InfoMessage;
import by.epam.training.helper.dao.exception.ConnectionPoolException;
import by.epam.training.helper.dao.pool.InitializationConnectionPool;

/**
 * Application Lifecycle Listener implementation class SourceListener
 * @author Nikolaev Ilya
 */
@WebListener
public class SourceListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(SourceListener.class);

    public SourceListener() {  
    }
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent destroyed)  { 
    	logger.info(InfoMessage.DESTROI_CONECTION_POOL);
		InitializationConnectionPool pool = InitializationConnectionPool.getInctance();
		try {
			
			pool.destroy();
			
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
		} catch (IOException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent initialized)  { 
    	logger.info(InfoMessage.INIT_CONECTION_POOL);System.out.println();
		InitializationConnectionPool pool = InitializationConnectionPool.getInctance();
		try {
			
			pool.init();
		} catch (ConnectionPoolException e) {
			logger.error(ErrorMessageDAO.ERROR_CONNECTION, e);
		}
    }
	
	
}

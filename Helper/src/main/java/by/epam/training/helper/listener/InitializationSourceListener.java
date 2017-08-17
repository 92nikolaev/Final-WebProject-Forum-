package by.epam.training.helper.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InitializationSourceListener
 *	@author Nikolaev Ilya
 */
@WebListener
public class InitializationSourceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent destroyed) {
	}
	@Override
	public void contextInitialized(ServletContextEvent initialized) {	
	}
	
}

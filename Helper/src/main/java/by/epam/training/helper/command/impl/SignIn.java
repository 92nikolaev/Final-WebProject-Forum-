package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
/**
 * @author Nikolaev Ilya
 *
 */
public class SignIn implements Command{
	private static final Logger logger = LogManager.getLogger(SignIn.class);

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, CommandException {
		String login = request.getParameter(ParameterName.USER_LOGIN);
		String password = request.getParameter(ParameterName.USER_PASSWORD);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		User user = null;
		HttpSession session = null;
		
		try {
			user = userService.signIn(login, password);
			session = request.getSession(true);
			session.setAttribute(ParameterName.USER, user);
			session.setAttribute(ParameterName.LOGGED, true);
			response.sendRedirect("controller?command=home");	
		} catch (ServiceException e) {
			response.sendRedirect("signIn.jsp");
			e.printStackTrace();
			logger.error(ErrorMessage.ERROR_SIGN_IN, e);
		}
		
		
	}
}

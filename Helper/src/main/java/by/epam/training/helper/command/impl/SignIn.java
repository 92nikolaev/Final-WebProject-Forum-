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
import by.epam.training.helper.constant.ErrorMessageCommand;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
/**
 *  Class is used for sign in.
 * @author Nikolaev Ilya
 *
 */
public class SignIn implements Command{
	private static final Logger logger = LogManager.getLogger(SignIn.class);
	/**
	 * Take the input parameters from the HttpServletRequest and
	 *  send it to the Service layer
	 *  @param request - request from client to get parameters to work with it
	 *  @param response - send response to client with parameters to work with on client side
	 *  @throws IOException  
     * 	@throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
			response.sendRedirect(Url.REDIRECT_HOME_PAGE);	
		} catch (ServiceException e) {
			String errorStatus = e.getMessage();
			logger.error(ErrorMessageCommand.ERROR_SIGN_IN);
			response.sendRedirect(Url.REDIRECT_SIGN_IN + errorStatus);
		}	
	}
}

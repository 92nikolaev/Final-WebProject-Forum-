package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
/**
 * Class is used for sign up.
 * @author Nikolaev Ilya
 *
 */
public class SignUp implements Command {
	private static final Logger logger = LogManager.getLogger(SignUp.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		User user = new User();
		user.setName(request.getParameter(ParameterName.USER_NAME));
		user.setSurname(request.getParameter(ParameterName.USER_SURNAME));
		user.setLogin(request.getParameter(ParameterName.USER_LOGIN));
		user.setEmail(request.getParameter(ParameterName.USER_EMAIL));
		
		String password = request.getParameter(ParameterName.USER_PASSWORD);
		String verificationPassword = request.getParameter(ParameterName.USER_VERIFICATION_PASSWORD);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		
		try {
			userService.signUp(user, password, verificationPassword);
			response.sendRedirect("successful.jsp");
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ParameterName.ERROR, true);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Url.SIGN_UP);
			dispatcher.forward(request, response);
			
		}

	}
}
